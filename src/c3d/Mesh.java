package c3d;

import java.util.List;
import java.util.function.Function;

import c3d.geometry.Point3D;
import c3d.geometry.Segment3D;

public interface Mesh {
    /**
     * 
     * @param transformation the transformation to apply to each point
     * @return a new mesh, with the transformation applied
     */
    Mesh transformed(Function<Double, Double> transformation);

    /**
     * 
     * @param vector the vector to add to each point
     * @return a new mesh, with the vector applied
     */
    Mesh translated(Point3D vector);

    /**
     * 
     * @return the list of segments
     */
    List<Segment3D> getSegments();

    /**
     * 
     * @return the scale of the mesh
     */
    double getScale();

    /**
     * 
     * @param segments the list of segments to generate the mesh from
     * @return a new mesh with the provided segments
     */
    static Mesh fromSegments(final List<Segment3D> segments) {
        return new MeshImpl(segments);
    }

}