package c3d.geometry;

public interface Line {

	double getM();

	double getQ();

	double getZero();

	double solveFor(double x);

	static Line fromPoints(final Point2D a, final Point2D b) {
		return new LineImpl(a, b);
	}

}