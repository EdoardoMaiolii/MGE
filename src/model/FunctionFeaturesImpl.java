package model;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import c3d.geometry.*;
import libraries.*;
import function.*;
public class FunctionFeaturesImpl implements FunctionFeatures{
	private AlgebricFunctionImpl<?> function ;
	private Double rate;
	private Pair<Double,Double> interval;
	private List<Character> variables = new ArrayList<>();
	private Integer decimalPrecision ;
	private List<PointND> points = new ArrayList<>();
	
	protected FunctionFeaturesImpl(AlgebricFunctionImpl<?> function , Pair<Double,Double> interval , Double rate) {
		this.function = function;
		this.interval = interval;
		this.rate = rate;
		this.variables = getParameters(function);
		this.decimalPrecision = getDecimalPrecision(rate);
		this.points = calculatePoints(variables.size(),interval);
	}
	
	private List<PointND> calculatePoints(final Integer dimentions ,final Pair<Double,Double> interval){
		final int nPoint = (int)(Math.abs(interval.getFst()-interval.getSnd())/rate);
		return IntStream.range(0,(int)Math.pow(nPoint+1, dimentions))
		.<PointND>mapToObj(i -> {
			List<Double> tmpList = IntStream.range(0,dimentions)
					.mapToDouble(j -> Math.floor((((int)(i/Math.pow(nPoint+1, j))%(nPoint+1))*rate+interval.getFst())*Math.pow(10, decimalPrecision))/Math.pow(10, decimalPrecision))
					.boxed().collect(Collectors.toList());
					tmpList.add(function.resolve(variables, tmpList));
			return new PointNDImpl(tmpList);
		}).collect(Collectors.toList());
	}
	
	private List<Character> getParameters(AlgebricFunctionImpl<?> function){
		List<Character> variables = new ArrayList<>();
		if(function.isVariable()){
			variables.add((Character)function.getType());
		}
		else if (function.isMathFunction()){
			function.getParameters().get().forEach(i -> variables.addAll(getParameters(i)));
		}
		return variables.stream().distinct().collect(Collectors.toList());
	}
	
	private Integer getDecimalPrecision(Double value) {
		var string = String.valueOf(value);
		if (string.indexOf('.')==-1)
			return 0;
		else
			return (string.length()-1)-string.indexOf('.');
	}
	private Stream<Segment3D> getRealSegmentList(List<PointND> points , Function<Integer,Integer> posDetector) {
		return IntStream.range(0,points.size()-1).filter(i -> points.get(i).getCoordinates().stream().filter(a->Double.isFinite(a)).count()==points.get(i).getCoordinates().size()&&points.get(i+1).getCoordinates().stream().filter(a->Double.isFinite(a)).count()==points.get(i+1).getCoordinates().size())
				.mapToObj(i -> new Pair<PointND,PointND>(points.get(posDetector.apply(i)),points.get(posDetector.apply(i+1))))
				.<Segment3D>map(i -> Segment3D.fromPoints(
				Point3D.fromDoubles(i.getFst().getCoordinates().get(0),i.getFst().getCoordinates().get(1),i.getFst().getCoordinates().get(2)), 
				Point3D.fromDoubles(i.getSnd().getCoordinates().get(0),i.getSnd().getCoordinates().get(1),i.getSnd().getCoordinates().get(2))));
	}
	public List<PointND> getPointsInDomain(){
		return points.stream().filter(i ->Double.isFinite(i.getCoordinates().get(i.getCoordinates().size()-1))).collect(Collectors.toList());
	}
	
	public List<PointND> getPointsOutOfDomain(){
		return points.stream().filter(i ->!Double.isFinite(i.getCoordinates().get(i.getCoordinates().size()-1))).collect(Collectors.toList());
	}
	
	public PointND getAbsoluteMax(){
		return getPointsInDomain().stream().reduce((a,b) -> {
			if (a.getCoordinates().get(a.getCoordinates().size()-1)>b.getCoordinates().get(b.getCoordinates().size()-1))
				return a;
			else 
				return b;
		}).get();
	}
	
	public PointND getAbsoluteMin(){
		return getPointsInDomain().stream().reduce((a,b) -> {
			if (a.getCoordinates().get(a.getCoordinates().size()-1)>b.getCoordinates().get(b.getCoordinates().size()-1))
				return b;
			else 
				return a;
		}).get();
	}
	public List<Segment3D> getPolygonalModel(){
		switch (variables.size()) {
		case 1 : {
			return getRealSegmentList(points.stream().map(i -> new PointNDImpl(Stream.concat(i.getCoordinates().stream(),List.of(0.0).stream()).collect(Collectors.toList()))).collect(Collectors.toList()),(i) -> i).collect(Collectors.toList());
		}
		case 2 : {
			var x = (Math.abs(interval.getFst()- interval.getSnd()))/rate+1;
			var y = (Math.abs(interval.getFst()- interval.getSnd()))/rate+1;
			var tmp1 = getRealSegmentList(points, i->i).filter(i -> i.getA().getX()==i.getB().getX()).collect(Collectors.toList());
			var tmp2 = getRealSegmentList(points,i->(int)((i%x)*y+i/x)).filter(i -> i.getA().getX()==i.getB().getX()).collect(Collectors.toList());
			return Stream.concat(getRealSegmentList(points, i->i).filter(i -> i.getA().getY()==i.getB().getY()), getRealSegmentList(points,i->(int)((i%x)*y+i/x)).filter(i -> i.getA().getY()==i.getB().getY())).collect(Collectors.toList());
		}
		default : return null;
		}
	}
}
