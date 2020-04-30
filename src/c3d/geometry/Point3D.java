package c3d.geometry;

import java.util.function.Function;

public interface Point3D {

	double getX();

	double getY();

	double getZ();

	Point3D rotated(double angleXY, double angleYZ);

	Point3D translated(double x, double y, double z);

	Point2D render();

	Point2D render(Point2D pointOfView);

	Point3D transformed(Function<Double, Double> transformation);

	static Point3D origin() {
		return new Point3DImpl(0, 0, 0);
	}

	static Point3D fromDoubles(final double x, final double y, final double z) {
		return new Point3DImpl(x, y, z);
	}

}