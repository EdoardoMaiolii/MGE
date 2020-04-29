package c3d.geometry;

import java.awt.Color;

public interface Segment3D {

	Point3D getA();

	Point3D getB();

	Color getColor();

	Segment3D translated(double x, double y, double z);

	Segment3D rotated(double angleXY, double angleYZ);
	
	Segment2D render();
	
	public static Segment3D fromPoints(Point3D a, Point3D b) {
		return new Segment3DImpl(a, b);
	}

}