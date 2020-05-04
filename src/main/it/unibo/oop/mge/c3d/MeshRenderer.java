package it.unibo.oop.mge.c3d;

import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

public interface MeshRenderer {
    /**
     * 
     * @param params the render parameters
     * @return the rendered segments, normalized from -1 to 1 in each axis
     */
    List<Segment2D> render(RenderParameters params);

    /**
     * 
     * @param segments the segments to render
     * @return a new MeshRenderer
     */
    static MeshRenderer fromSegments(final List<Segment3D> segments) {
        return fromMesh(Mesh.fromSegments(segments));
    }

    /**
     * 
     * @param mesh the mesh to render
     * @return a new MeshRenderer
     */
    static MeshRenderer fromMesh(final Mesh mesh) {
        return new MeshRendererImpl(mesh);
    }
}
