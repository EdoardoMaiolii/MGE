package c3d;

import java.util.List;

import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public interface MeshVisualizer {

	void setMesh(List<Segment3D> segments);

	void setRotationXY(double rotationXY);

	void setRotationYZ(double rotationYZ);

	void setTranslation(Point3D translation);

	List<Segment2D> render();
}