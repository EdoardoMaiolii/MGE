package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;
import it.unibo.oop.mge.color.ColorGenerator;
import it.unibo.oop.mge.color.ColorGeneratorImpl;
import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.libraries.PointND;
import it.unibo.oop.mge.libraries.PointNDImpl;
import it.unibo.oop.mge.libraries.Variable;

public class FunctionFeaturesImpl implements FunctionFeatures {
    private Double rate;
    private Pair<Double, Double> interval;
    private Integer width;
    private Integer decimalPrecision;
    private List<Point3D> points;
    private ColorGenerator cg;

    protected FunctionFeaturesImpl(final AlgebricFunction function, final Pair<Double, Double> interval,
            final Double rate, final Optional<VariableColor> varColor, final Optional<Color> staticColor,
            final Integer decimalPrecision) {
        this.interval = interval;
        this.rate = rate;
        this.decimalPrecision = decimalPrecision;
        this.width = (int) (Math.abs(interval.getFst() - interval.getSnd()) / rate);
        this.points = pointCaster(getPointsFromFunction(function));
        if (varColor.isPresent()) {
            this.cg = new ColorGeneratorImpl(varColor.get(), this.getPointOfAbsoluteMin().getZ(),
                    this.getPointOfAbsoluteMax().getZ());
        } else {
            this.cg = new ColorGeneratorImpl(staticColor.get());
        }
    }

    private List<Point3D> pointCaster(final List<PointND> points) {
        return points.stream()
                .<Point3D>map(
                        i -> Point3D.fromDoubles(i.getValues().get(0), i.getValues().get(1), i.getValues().get(2)))
                .collect(Collectors.toList());
    }

    private List<Point3D> getRealPoints(final List<Point3D> points) {
        return points.stream().filter(i -> Double.isFinite(i.getZ())).collect(Collectors.toList());
    }

    private double troncateDouble(final Double value) {
        return Math.floor(Math.pow(10, decimalPrecision) * value) / Math.pow(10, decimalPrecision);
    }

    private List<PointND> getPointsFromFunction(final AlgebricFunction function) {
        /*
         * This function takes the number of the point and the number of the coordinate
         * and return the coordinate
         */
        BiFunction<Integer, Integer, Double> myfunc = (i,
                j) -> (((int) (i / Math.pow(this.width + 1, j)) % (this.width + 1)) * this.rate
                        + this.interval.getFst());

        return IntStream.range(0, (int) Math.pow(this.width + 1, Variable.getListFromEnum().size()))
                .<PointND>mapToObj(i -> {
                    LinkedHashMap<Variable, Double> coordinates = Arrays.asList(Variable.values()).stream()
                            .collect(Collectors.toMap(a -> a,
                                    a -> troncateDouble(myfunc.apply(i, Arrays.asList(Variable.values()).indexOf(a))),
                                    (x, y) -> y, LinkedHashMap::new));
                    return new PointNDImpl(Stream
                            .concat(coordinates.values().stream(), List.of(function.resolve(coordinates)).stream())
                            .collect(Collectors.toList()));
                }).collect(Collectors.toList());
    }

    private List<Segment3D> getRealSegmentList(final List<Point3D> points,
            final Function<Integer, Integer> posDetector) {
        return IntStream.range(0, points.size() - 1)
                .mapToObj(i -> new Pair<Point3D, Point3D>(points.get(posDetector.apply(i)),
                        points.get(posDetector.apply(i + 1))))
                .filter(i -> Double.isFinite(i.getFst().getZ()) && Double.isFinite(i.getSnd().getZ()))
                .map(i -> Segment3D.fromPoints(i.getFst(), i.getSnd(),
                        cg.getColorFromDouble((i.getFst().getZ() + i.getSnd().getZ()) / 2)))
                .collect(Collectors.toList());
    }

    public final List<Segment3D> getPolygonalModel() {
        return Stream
                .concat(getRealSegmentList(points, i -> i).stream().filter(i -> i.getA().getY() == i.getB().getY()),
                        getRealSegmentList(points, i -> (int) ((i % (width + 1)) * (width + 1) + i / (width + 1)))
                                .stream().filter(i -> i.getA().getX() == i.getB().getX()))
                .collect(Collectors.toList());
    }

    @Override
    public final List<Segment3D> getPoligonalAxis() {
        return List.of(
                Segment3D.fromPoints(Point3D.fromDoubles(interval.getFst(), 0, 0),
                        Point3D.fromDoubles(interval.getSnd(), 0, 0)),
                Segment3D.fromPoints(Point3D.fromDoubles(0, interval.getFst(), 0),
                        Point3D.fromDoubles(0, interval.getSnd(), 0)),
                Segment3D.fromPoints(Point3D.fromDoubles(0, 0, interval.getFst()),
                        Point3D.fromDoubles(0, 0, interval.getSnd())));
    }

    public final Point3D getPointOfAbsoluteMax() {
        return this.getRealPoints(points).stream().max((i, j) -> Double.compare(i.getZ(), j.getZ())).get();
    }

    public final Point3D getPointOfAbsoluteMin() {
        return this.getRealPoints(points).stream().min((i, j) -> Double.compare(i.getZ(), j.getZ())).get();
    }
}
