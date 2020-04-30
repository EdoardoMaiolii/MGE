import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import functions.*;
import libraries.*;

public class FunctionFeatures {
	private AlgebricFunction<?> function ;
	private Double rate;
	private List<Pair<Double,Double>> intervals;
	private Boolean setted = false;
	private List<Character> variables = new ArrayList<>();
	private List<NDPoint> points = new ArrayList<>();

	public FunctionFeatures(AlgebricFunction<?> function) {
		this.function = function;
	}
	
	private Boolean isReady() {
		return setted && (intervals.stream().filter(i -> i.getFst()>i.getSnd()).count()==0) &&  (rate>0 && intervals.stream().filter(i -> rate>Math.abs(i.getFst()-i.getSnd())).count()==0) && intervals.size()==variables.size();  
	}
	private void printNotReady() {
		System.out.println("Settings wrongs or unsetted");
	}
	private List<List<Double>> getCombinations(Integer dimentions ,List<Pair<Double,Double>> intervals){
		if (dimentions == 0)
			return List.of(List.of());
		else {
			return Stream.iterate(intervals.get(dimentions-1).getFst(), i -> i + rate)
			.limit((long) Math.ceil(Math.abs(intervals.get(dimentions-1).getFst()-intervals.get(dimentions-1).getSnd())/rate))
			.map(i ->List.of(i))
			.map(a -> getCombinations(dimentions-1,intervals).stream()
			.<List<Double>>map(i ->
			Stream.concat(a.stream(), i.stream()).collect(Collectors.toList()))
			.collect(Collectors.toList()))
			.flatMap(x -> x.stream())
            .collect(Collectors.toList());
		}
	}
	
	private List<NDPoint> calculatePoints() {
		return getCombinations(intervals.size(),intervals).stream().map(i -> {
			i.add(function.resolve(variables, i));
			return new NDPoint(i);
			}).collect(Collectors.toList());
	}
	private List<Character> findAllParameters(AlgebricFunction<?> function){
		List<Character> variables = new ArrayList<>();
		if(function.isVariable()){
			variables.add((Character)function.getInfo());
		}
		else if (function.isMathFunction()){
			function.getParameters().get().forEach(i -> variables.addAll(findAllParameters(i)));
		}
		return variables;
	}
	private List<Character> getParameters(AlgebricFunction<?> function) {
		return findAllParameters(function).stream().distinct().collect(Collectors.toList());
	}
	public void setSettings (List<Pair<Double,Double>> ranges, Double rate) {
		this.intervals = ranges;
		this.rate = rate;
		this.setted = true;
		this.variables = getParameters(function);
		if (isReady()) {
			points = calculatePoints();
		}
		else 
			printNotReady();
	}
	public List<NDPoint> getPointsInDomain(){
		return points.stream().filter(i ->Double.isFinite(i.getCoordinates().get(i.getCoordinates().size()-1))).collect(Collectors.toList());
	}
	public List<NDPoint> getPointsOutOfDomain(){
		return points.stream().filter(i ->!Double.isFinite(i.getCoordinates().get(i.getCoordinates().size()-1))).collect(Collectors.toList());
	}
	public NDPoint getAbsoluteMax(){
		return points.stream().reduce((a,b) -> {
			if (a.getCoordinates().get(a.getCoordinates().size()-1)>b.getCoordinates().get(b.getCoordinates().size()-1))
				return a;
			else 
				return b;
		}).get();
	}
	public NDPoint getAbsoluteMin(){
		return points.stream().reduce((a,b) -> {
			if (a.getCoordinates().get(a.getCoordinates().size()-1)>b.getCoordinates().get(b.getCoordinates().size()-1))
				return b;
			else 
				return a;
		}).get();
	}
}
