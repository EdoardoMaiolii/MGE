package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


import it.unibo.oop.mge.Color.ColorGenerator;
import it.unibo.oop.mge.Color.ColorGeneratorImpl;
import it.unibo.oop.mge.Color.VariableColor;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.libraries.PointND;
import it.unibo.oop.mge.libraries.PointNDImpl;

public class FunctionFeaturesImpl implements FunctionFeatures {
    private AlgebricFunction<?> function;
    private Double rate;
    private Pair<Double, Double> interval;
    private List<Character> variables = new ArrayList<>();
    private Integer decimalPrecision;
    private List<PointND> points;
    private List<PointND> realPoints = new ArrayList<>();
    private int dimentions;
    private ColorGenerator cg;

    protected FunctionFeaturesImpl(final AlgebricFunction<?> function, final Pair<Double, Double> interval,
            final Double rate, final Optional<VariableColor> opColor, final Optional<Color> staticColor,
            final Integer decimalPrecision) {
        this.function = function;
        this.interval = interval;
        this.rate = rate;
        this.decimalPrecision = decimalPrecision;
        this.variables = getParameters(function);
        this.points = calculatePoints(variables.size(), interval);
        this.realPoints = getRealPoints();
        this.dimentions = variables.size() + 1;
        if (opColor.isEmpty()) {
            this.cg = new ColorGeneratorImpl(staticColor.get());
        } else {
            var point1 = new Pair<Double, Integer>(
                    castDouble(this.getPointOfAbsoluteMin().getCoordinates().get(dimentions - 1), (i -> Math.floor(i))),
                    0);
            var point2 = new Pair<Double, Integer>(
                    castDouble(this.getPointOfAbsoluteMax().getCoordinates().get(dimentions - 1), (i -> Math.ceil(i))),
                    255);
            this.cg = new ColorGeneratorImpl(opColor.get(), point1, point2);
        }
    }

    private double castDouble(final Double value, final Function<Double, Double> pattern) {
        return pattern.apply(Math.pow(10, decimalPrecision) * value) / Math.pow(10, decimalPrecision);
    }

    private List<PointND> calculatePoints(final Integer dimentions, final Pair<Double, Double> interval) {
        final int nPoint = (int) (Math.abs(interval.getFst() - interval.getSnd()) / rate);
        return IntStream.range(0, (int) Math.pow(nPoint + 1, dimentions)).<PointND>mapToObj(i -> {
            List<Double> coordinates = IntStream.range(0, dimentions).mapToDouble(
                    j -> Math.floor((((int) (i / Math.pow(nPoint + 1, j)) % (nPoint + 1)) * rate + interval.getFst())
                            * Math.pow(10, decimalPrecision)) / Math.pow(10, decimalPrecision))
                    .boxed().collect(Collectors.toList());
            coordinates.add(function.resolve(variables, coordinates));
            return new PointNDImpl(coordinates);
        }).collect(Collectors.toList());
    }

    private List<Character> getParameters(final AlgebricFunction<?> function) {
        List<Character> variables = new ArrayList<>();
        if (function.isVariable()) {
            variables.add((Character) function.getType());
        } else if (function.isMathFunction()) {
            function.getParameters().get().forEach(i -> System.out.println("fdas"));
            function.getParameters().get().forEach(i -> variables.addAll(getParameters(i)));
        }
        return variables.stream().distinct().collect(Collectors.toList());
    }

    private List<Segment3D> getRealSegmentList(final List<PointND> points,
            final Function<Integer, Integer> posDetector) {
        return IntStream.range(0, points.size() - 1)
                .mapToObj(i -> new Pair<PointND, PointND>(points.get(posDetector.apply(i)),
                        points.get(posDetector.apply(i + 1))))
                .filter(i -> i.getFst().getCoordinates().stream().filter(a -> Double.isFinite(a)).count() == i.getFst()
                        .getCoordinates().size()
                        && i.getSnd().getCoordinates().stream().filter(a -> Double.isFinite(a)).count() == i.getSnd()
                                .getCoordinates().size())
                .<Segment3D>map(i -> Segment3D.fromPoints(
                        Point3D.fromDoubles(i.getFst().getCoordinates().get(0), i.getFst().getCoordinates().get(1),
                                i.getFst().getCoordinates().get(2)),
                        Point3D.fromDoubles(i.getSnd().getCoordinates().get(0), i.getSnd().getCoordinates().get(1),
                                i.getSnd().getCoordinates().get(2)),
                        cg.getColorFromDouble(i.getFst().getCoordinates().get(2))))
                .collect(Collectors.toList());
    }

    public final List<PointND> getRealPoints() {
        return points.stream().filter(i -> Double.isFinite(i.getCoordinates().get(i.getCoordinates().size() - 1)))
                .collect(Collectors.toList());
    }

    public final List<PointND> getImmaginaryPoints() {
        return points.stream().filter(i -> !Double.isFinite(i.getCoordinates().get(i.getCoordinates().size() - 1)))
                .collect(Collectors.toList());
    }

    public final PointND getPointOfAbsoluteMax() {
        return realPoints.stream().max((i, j) -> Double.compare(i.getCoordinates().get(dimentions - 1),
                j.getCoordinates().get(dimentions - 1))).get();
    }

    public final PointND getPointOfAbsoluteMin() {
        return realPoints.stream().min((i, j) -> Double.compare(i.getCoordinates().get(dimentions - 1),
                j.getCoordinates().get(dimentions - 1))).get();
    }

    public final List<Segment3D> getPolygonalModel() {
        switch (variables.size()) {
        case 1:
            return getRealSegmentList(realPoints.stream().map(i -> new PointNDImpl(
                    Stream.concat(i.getCoordinates().stream(), List.of(0.0).stream()).collect(Collectors.toList())))
                    .collect(Collectors.toList()), (i) -> i);
        case 2:
            final var x = (Math.abs(interval.getFst() - interval.getSnd())) / rate + 1;
            final var y = (Math.abs(interval.getFst() - interval.getSnd())) / rate + 1;
            return Stream.concat(
                    getRealSegmentList(points, i -> i).stream().filter(i -> i.getA().getY() == i.getB().getY()),
                    getRealSegmentList(points, i -> (int) ((i % x) * y + i / x)).stream()
                            .filter(i -> i.getA().getX() == i.getB().getX()))
                    .collect(Collectors.toList());
        default:
            return null;
        }
    }

    @Override
    public final List<Segment3D> getPoligonalAxis() {
        Segment3D axisX = Segment3D.fromPoints(Point3D.fromDoubles(interval.getFst(), 0, 0),
                Point3D.fromDoubles(interval.getSnd(), 0, 0));
        switch (variables.size()) {
        case 1: {
            Segment3D axisY = Segment3D.fromPoints(
                    Point3D.fromDoubles(0, this.getPointOfAbsoluteMin().getCoordinates().get(1), 0),
                    Point3D.fromDoubles(0, this.getPointOfAbsoluteMax().getCoordinates().get(1), 0));
            return List.of(axisX, axisY);
        }
        case 2: {
            Segment3D axisY = Segment3D.fromPoints(Point3D.fromDoubles(0, interval.getFst(), 0),
                    Point3D.fromDoubles(0, interval.getSnd(), 0));
            Segment3D axisZ = Segment3D.fromPoints(
                    Point3D.fromDoubles(0, 0, this.getPointOfAbsoluteMin().getCoordinates().get(2)),
                    Point3D.fromDoubles(0, 0, this.getPointOfAbsoluteMax().getCoordinates().get(2)));
            return List.of(axisX, axisY, axisZ);
        }
        default:
            return null;
        }
    }
}
