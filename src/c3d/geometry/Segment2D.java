package c3d.geometry;

import java.awt.Color;
import java.util.function.Function;

public interface Segment2D {

	Point2D getA();

	Point2D getB();

	Color getColor();

	Segment2D transformed(Function<Double, Double> transformation);

	static Segment2D fromPoints(final Point2D a, final Point2D b) {
		return fromPoints(a, b, Color.BLACK);
	}

	static Segment2D fromPoints(final Point2D a, final Point2D b, final Color color) {
		return new Segment2DImpl(a, b, color);
	}

}