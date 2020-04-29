package c3d.geometry;

public class Point2DImpl implements Point2D {
	private final double x;
	private final double y;

	// package protected
	Point2DImpl(double x, double y) {
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
}
