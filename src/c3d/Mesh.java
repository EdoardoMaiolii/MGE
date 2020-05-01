package c3d;

import java.util.List;
import java.util.function.Function;

import c3d.geometry.Point3D;
import c3d.geometry.Segment3D;

public interface Mesh {

	Mesh transformed(Function<Double, Double> transformation);

	Mesh translated(Point3D vector);

	List<Segment3D> getSegments();

	double getScale();

	static Mesh fromSegments(final List<Segment3D> segments) {
		return new MeshImpl(segments);
	}

}