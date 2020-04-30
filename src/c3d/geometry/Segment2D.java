package c3d.geometry;

import java.awt.Color;

public interface Segment2D {

	Point2D getA();

	Point2D getB();

	Color getColor();

	public static Segment2D fromPoints(final Point2D a, final Point2D b) {
		return new Segment2DImpl(a, b);
	}

	public static Segment2D fromPoints(final Point2D a, final Point2D b, final Color color) {
		return new Segment2DImpl(a, b, color);
	}

}