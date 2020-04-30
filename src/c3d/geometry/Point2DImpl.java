package c3d.geometry;

import java.util.function.Function;

public class Point2DImpl implements Point2D {
	private final double x;
	private final double y;

	// package protected
	Point2DImpl(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public String toString() {
		return "Point2DImpl [x=" + x + ", y=" + y + "]";
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public Point2D transformed(Function<Double, Double> transformation) {
		return new Point2DImpl(transformation.apply(this.x), transformation.apply(this.y));
	}
}
