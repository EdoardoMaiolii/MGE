package c3d.geometry;

public interface Point3D {

	double getX();

	double getY();

	double getZ();

	Point3D rotated(double angleXY, double angleYZ);

	Point3D translate(double x, double y, double z);
	
	Point2D render();

	public static Point3D origin() {
		return new Point3DImpl(0, 0, 0);
	}
	
	public static Point3D fromDoubles(final double x, final double y, final double z) {
		return new Point3DImpl(x, y, z);
	}

}