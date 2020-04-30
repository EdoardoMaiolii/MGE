package c3d;

import java.util.List;

import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public interface Mesh {

	Mesh scaled(double scale);

	List<Segment2D> render(RenderParameters params);
	
	static Mesh fromSegments(final List<Segment3D> segments) {
		return new MeshImpl(segments);
	}

}