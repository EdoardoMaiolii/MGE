package c3d.geometry;

import java.awt.Color;

public class Segment2DImpl implements Segment2D {
	private final Point2D a;
	private final Point2D b;
	private final Color color;

	@Override
	public Point2D getA() {
		return a;
	}

	@Override
	public String toString() {
		return "Segment2DImpl [a=" + a + ", b=" + b + "]";
	}

	@Override
	public Point2D getB() {
		return b;
	}

	// package protected
	Segment2DImpl(Point2D a, Point2D b, Color color) {
		super();
		this.a = a;
		this.b = b;
		this.color = color;
	}
	
	Segment2DImpl(Point2D a, Point2D b) {
		this(a, b, Color.black);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}
