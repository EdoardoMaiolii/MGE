package c3d;

import java.util.List;

import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public interface ModelVisualizer {

	void setModel(List<Segment3D> model);

	void setRotationXY(double rotationXY);

	void setRotationYZ(double rotationYZ);

	void setTranslation(Point3D translation);

	List<Segment2D> render();
}