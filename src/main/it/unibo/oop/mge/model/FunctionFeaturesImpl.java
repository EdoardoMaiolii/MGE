package it.unibo.oop.mge.model;

import java.awt.Color;
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

public class FunctionFeaturesImpl implements FunctionFeatures {
    private static final List<Character> VARIABLES = List.of('x', 'y');
    private static final int MAXRGBVALUE = 255;
    private static final int MINRGBVALUE = 0;
    private Double rate;
    private Pair<Double, Double> interval;
    private Integer decimalPrecision;
    private List<Point3D> points;
    private List<Point3D> realPoints;
    private ColorGenerator cg;

    protected FunctionFeaturesImpl(final AlgebricFunction function, final Pair<Double, Double> interval,
            final Double rate, final Optional<VariableColor> varColor, final Optional<Color> staticColor,
            final Integer decimalPrecision) {
        this.interval = interval;
        this.rate = rate;
        this.decimalPrecision = decimalPrecision;
        this.points = getPointsFromFunction(function);
        this.realPoints = getRealPoints(points);
        if (varColor.isEmpty()) {
            this.cg = new ColorGeneratorImpl(staticColor.get());
        } else {
            this.cg = new ColorGeneratorImpl(varColor.get(),
                    new Pair<Double, Integer>(castDouble(this.getPointOfAbsoluteMin().getZ(), (i -> Math.floor(i))),
                            MINRGBVALUE),
                    new Pair<Double, Integer>(castDouble(this.getPointOfAbsoluteMax().getZ(), (i -> Math.ceil(i))),
                            MAXRGBVALUE));
        }
    }

    private double castDouble(final Double value, final Function<Double, Double> castingFunction) {
        return castingFunction.apply(Math.pow(10, decimalPrecision) * value) / Math.pow(10, decimalPrecision);
    }

    private List<Point3D> getPointsFromFunction(final AlgebricFunction function) {
        final int nPoint = (int) (Math.abs(interval.getFst() - interval.getSnd()) / rate);
        BiFunction<Integer, Integer, Double> myfunc = (i,
                j) -> (((int) (i / Math.pow(nPoint + 1, j)) % (nPoint + 1)) * rate + interval.getFst());
        return IntStream.range(0, (int) Math.pow(nPoint + 1, 2)).<Point3D>mapToObj(i -> {
            final Double x = castDouble(myfunc.apply(i, 0), a -> Math.floor(a));
            final Double y = castDouble(myfunc.apply(i, 1), a -> Math.floor(a));
            return Point3D.fromDoubles(x, y,
                    castDouble(function.resolve(VARIABLES, List.of(x, y)), a -> Math.floor(a)));
        }).collect(Collectors.toList());
    }

    private List<Segment3D> getRealSegmentList(final List<Point3D> points,
            final Function<Integer, Integer> posDetector) {
        return IntStream.range(0, points.size() - 1)
                .mapToObj(i -> new Pair<Point3D, Point3D>(points.get(posDetector.apply(i)),
                        points.get(posDetector.apply(i + 1))))
                .filter(i -> Double.isFinite(i.getFst().getZ()) && Double.isFinite(i.getSnd().getZ()))
                .<Segment3D>map(i -> Segment3D.fromPoints(i.getFst(), i.getSnd(),
                        cg.getColorFromDouble((i.getFst().getZ() + i.getSnd().getZ()) / 2)))
                .collect(Collectors.toList());
    }

    private List<Point3D> getRealPoints(final List<Point3D> points) {
        return points.stream().filter(i -> Double.isFinite(i.getZ())).collect(Collectors.toList());
    }

    private List<Point3D> getImmaginaryPoints(final List<Point3D> points) {
        return points.stream().filter(i -> !Double.isFinite(i.getZ())).collect(Collectors.toList());
    }

    public final Point3D getPointOfAbsoluteMax() {
        return realPoints.stream().max((i, j) -> Double.compare(i.getZ(), j.getZ())).get();
    }

    public final Point3D getPointOfAbsoluteMin() {
        return realPoints.stream().min((i, j) -> Double.compare(i.getZ(), j.getZ())).get();
    }

    public final List<Segment3D> getPolygonalModel() {
        final var widthX = (Math.abs(interval.getFst() - interval.getSnd())) / rate + 1;
        final var widthY = (Math.abs(interval.getFst() - interval.getSnd())) / rate + 1;
        return Stream
                .concat(getRealSegmentList(points, i -> i).stream().filter(i -> i.getA().getY() == i.getB().getY()),
                        getRealSegmentList(points, i -> (int) ((i % widthX) * widthY + i / widthX)).stream()
                                .filter(i -> i.getA().getX() == i.getB().getX()))
                .collect(Collectors.toList());
    }

    @Override
    public final List<Segment3D> getPoligonalAxis() {
        Segment3D axisX = Segment3D.fromPoints(Point3D.fromDoubles(interval.getFst(), 0, 0),
                Point3D.fromDoubles(interval.getSnd(), 0, 0));
        Segment3D axisY = Segment3D.fromPoints(Point3D.fromDoubles(0, interval.getFst(), 0),
                Point3D.fromDoubles(0, interval.getSnd(), 0));
        Segment3D axisZ = Segment3D.fromPoints(Point3D.fromDoubles(0, 0, this.getPointOfAbsoluteMin().getZ()),
                Point3D.fromDoubles(0, 0, this.getPointOfAbsoluteMax().getZ()));
        return List.of(axisX, axisY, axisZ);
    }
}
