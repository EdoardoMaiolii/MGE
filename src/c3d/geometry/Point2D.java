package c3d.geometry;

import java.util.function.Function;

public interface Point2D {

	double getX();

	double getY();

	Point2D transformed(Function<Double, Double> transformation);

	static Point2D fromDoubles(final double x, final double y) {
		return new Point2DImpl(x, y);
	}

	static Point2D origin() {
		return fromDoubles(0, 0);
	}

}