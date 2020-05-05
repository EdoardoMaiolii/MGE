package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;
import it.unibo.oop.mge.function.AlgebricFunctionImpl;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.libraries.PointND;
import it.unibo.oop.mge.libraries.PointNDImpl;

public class FunctionFeaturesImpl implements FunctionFeatures {
    private AlgebricFunctionImpl<?> function;
    private Double rate;
    private Pair<Double, Double> interval;
    private List<Character> variables = new ArrayList<>();
    private Integer decimalPrecision;
    private List<PointND> points = new ArrayList<>();

    protected FunctionFeaturesImpl(final AlgebricFunctionImpl<?> function, final Pair<Double, Double> interval,
            final Double rate) {
        this.function = function;
        this.interval = interval;
        this.rate = rate;
        this.variables = getParameters(function);
        this.decimalPrecision = getDecimalPrecision(rate);
        this.points = calculatePoints(variables.size(), interval);
    }

    private List<PointND> calculatePoints(final Integer dimentions, final Pair<Double, Double> interval) {
        final int nPoint = (int) (Math.abs(interval.getFst() - interval.getSnd()) / rate);
        return IntStream.range(0, (int) Math.pow(nPoint + 1, dimentions)).<PointND>mapToObj(i -> {
            List<Double> tmpList = IntStream.range(0, dimentions).mapToDouble(
                    j -> Math.floor((((int) (i / Math.pow(nPoint + 1, j)) % (nPoint + 1)) * rate + interval.getFst())
                            * Math.pow(10, decimalPrecision)) / Math.pow(10, decimalPrecision))
                    .boxed().collect(Collectors.toList());
            tmpList.add(function.resolve(variables, tmpList));
            return new PointNDImpl(tmpList);
        }).collect(Collectors.toList());
    }

    private List<Character> getParameters(final AlgebricFunctionImpl<?> function) {
        List<Character> variables = new ArrayList<>();
        if (function.isVariable()) {
            variables.add((Character) function.getType());
        } else if (function.isMathFunction()) {
            function.getParameters().get().forEach(i -> variables.addAll(getParameters(i)));
        }
        return variables.stream().distinct().collect(Collectors.toList());
    }

    private Integer getDecimalPrecision(final Double value) {
        var string = String.valueOf(value);
        if (string.indexOf('.') == -1) {
            return 0;
        } else {
            return (string.length() - 1) - string.indexOf('.');
        }
    }

    private Stream<Segment3D> getRealSegmentList(final List<PointND> points,
            final Function<Integer, Integer> posDetector) {
        return IntStream.range(0, points.size() - 1)
                .filter(i -> points.get(i).getCoordinates().stream().filter(a -> Double.isFinite(a)).count() == points
                        .get(i).getCoordinates().size()
                        && points.get(i + 1).getCoordinates().stream().filter(a -> Double.isFinite(a)).count() == points
                                .get(i + 1).getCoordinates().size())
                .mapToObj(i -> new Pair<PointND, PointND>(points.get(posDetector.apply(i)),
                        points.get(posDetector.apply(i + 1))))
                .<Segment3D>map(i -> Segment3D.fromPoints(
                        Point3D.fromDoubles(i.getFst().getCoordinates().get(0), i.getFst().getCoordinates().get(1),
                                i.getFst().getCoordinates().get(2)),
                        Point3D.fromDoubles(i.getSnd().getCoordinates().get(0), i.getSnd().getCoordinates().get(1),
                                i.getSnd().getCoordinates().get(2))));
    }

    public final List<PointND> getPointsInDomain() {
        return points.stream().filter(i -> Double.isFinite(i.getCoordinates().get(i.getCoordinates().size() - 1)))
                .collect(Collectors.toList());
    }

    public final List<PointND> getPointsOutOfDomain() {
        return points.stream().filter(i -> !Double.isFinite(i.getCoordinates().get(i.getCoordinates().size() - 1)))
                .collect(Collectors.toList());
    }

    public final PointND getAbsoluteMax() {
        return getPointsInDomain().stream().reduce((a, b) -> {
            if (a.getCoordinates().get(a.getCoordinates().size() - 1) > b.getCoordinates()
                    .get(b.getCoordinates().size() - 1))
                return a;
            else
                return b;
        }).get();
    }

    public final PointND getAbsoluteMin() {
        return getPointsInDomain().stream().reduce((a, b) -> {
            if (a.getCoordinates().get(a.getCoordinates().size() - 1) > b.getCoordinates()
                    .get(b.getCoordinates().size() - 1))
                return b;
            else
                return a;
        }).get();
    }

    public final List<Segment3D> getPolygonalModel() {
        switch (variables.size()) {
        case 1:
            return getRealSegmentList(points.stream()
                    .map(i -> new PointNDImpl(Stream.concat(i.getCoordinates().stream(), List.of(0.0).stream())
                            .collect(Collectors.toList())))
                    .collect(Collectors.toList()), (i) -> i).collect(Collectors.toList());
        case 2:
            final var x = (Math.abs(interval.getFst() - interval.getSnd())) / rate + 1;
            final var y = (Math.abs(interval.getFst() - interval.getSnd())) / rate + 1;
            return Stream.concat(getRealSegmentList(points, i -> i).filter(i -> i.getA().getY() == i.getB().getY()),
                    getRealSegmentList(points, i -> (int) ((i % x) * y + i / x))
                            .filter(i -> i.getA().getX() == i.getB().getX()))
                    .collect(Collectors.toList());
        default:
            return null;
        }
    }

    @Override
    public final List<Segment3D> getPoligonalAxis() {
        Segment3D axisX = Segment3D.fromPoints(Point3D.fromDoubles(interval.getFst(), 0, 0),
                Point3D.fromDoubles(interval.getSnd(), 0, 0), Color.GREEN);
        switch (variables.size()) {
        case 1: {
            Segment3D axisY = Segment3D.fromPoints(
                    Point3D.fromDoubles(0, this.getAbsoluteMin().getCoordinates().get(1), 0),
                    Point3D.fromDoubles(0, this.getAbsoluteMax().getCoordinates().get(1), 0), Color.ORANGE);
            return List.of(axisX, axisY);
        }
        case 2: {
            Segment3D axisY = Segment3D.fromPoints(Point3D.fromDoubles(0, interval.getFst(), 0),
                    Point3D.fromDoubles(0, interval.getSnd(), 0), Color.ORANGE);
            Segment3D axisZ = Segment3D.fromPoints(
                    Point3D.fromDoubles(0, 0, this.getAbsoluteMin().getCoordinates().get(2)),
                    Point3D.fromDoubles(0, 0, this.getAbsoluteMax().getCoordinates().get(2)), Color.BLUE);
            return List.of(axisX, axisY, axisZ);
        }
        default:
            return null;
        }
    }
}
