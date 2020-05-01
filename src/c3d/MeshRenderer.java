package c3d;

import java.util.List;

import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public interface MeshRenderer {
    List<Segment2D> render(RenderParameters params);

    static MeshRenderer fromSegments(final List<Segment3D> segments) {
        return fromMesh(Mesh.fromSegments(segments));
    }

    static MeshRenderer fromMesh(final Mesh mesh) {
        return new RenderableMeshImpl(mesh);
    }
}