package c3d;

import c3d.geometry.Point2D;
import c3d.geometry.Point3D;

public interface RenderParameters {
	double rotationXY();

	double rotationYZ();

	Point3D translation();

	Point2D pointOfView();
}
