package model;

import java.awt.Color;

public interface Segment3D {

	Point3D getA();

	Point3D getB();
	
	Color getColor();

	Segment3D translated(double x, double y, double z);

	Segment3D rotated(double angleXY, double angleYZ);

}