package c3d.geometry;

public interface Point2D {

	double getX();

	double getY();

	public static Point2D fromDoubles(final double x, final double y) {
		return new Point2DImpl(x, y);
	}

	public static Point2D origin() {
		return fromDoubles(0, 0);
	}

}