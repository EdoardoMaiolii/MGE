package c3d.geometry;

import java.awt.Color;

public interface Segment2D {

	Point2D getA();

	Point2D getB();

	Color getColor();

	public static Segment2D fromPoints(Point2D a, Point2D b) {
		return new Segment2DImpl(a, b);
	}
	public static Segment2D fromPoints(Point2D a, Point2D b, Color color) {
		return new Segment2DImpl(a, b, color);
	}

}