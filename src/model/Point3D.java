package model;

public interface Point3D {

	double getX();

	double getY();

	double getZ();

	Point3D rotated(double angleXY, double angleYZ);

	Point3D translate(double x, double y, double z);

}