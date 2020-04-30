package c3d.geometry;

import java.awt.Color;
import java.util.function.Function;

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
	Segment2DImpl(final Point2D a, final Point2D b, final Color color) {
		super();
		this.a = a;
		this.b = b;
		this.color = color;
	}

	Segment2DImpl(final Point2D a, final Point2D b) {
		this(a, b, Color.black);
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Segment2D transformed(Function<Double, Double> transformation) {
		return new Segment2DImpl(this.a.transformed(transformation), this.b.transformed(transformation));
	}
}
