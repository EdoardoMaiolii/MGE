package c3d.geometry;

public interface Line {

	double getM();

	double getQ();

	double getZero();

	double solveFor(double x);
	
	public static Line fromPoints(Point2D a, Point2D b) {
		return new LineImpl(a, b);
	}

}