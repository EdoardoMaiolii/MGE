package c3d.geometry;

public interface Point2D {

	double getX();

	double getY();

	public static Point2D fromDoubles(double x, double y) {
		return new Point2DImpl(x, y);
	}

	public static Point2D origin() {
		return fromDoubles(0, 0);
	}

}