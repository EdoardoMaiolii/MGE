package it.unibo.oop.mge.c3d;

import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

public interface MeshVisualizer {

    /**
     * 
     * @param segments the segments to render
     */
    void setMesh(List<Segment3D> segments);

    /**
     * 
     * @param rotationXY the rotation to apply in the axis XY
     */
    void setRotationXY(double rotationXY);

    /**
     * 
     * @return the current rotation in the axis XY
     */
    double getRotationXY();

    /**
     * 
     * @param rotationYZ the rotation to apply in the axis YZ
     */
    void setRotationYZ(double rotationYZ);

    /**
     * 
     * @return the current rotation in the axis YZ
     */
    double getRotationYZ();

    /**
     * 
     * @param translation the translation to apply
     */
    void setTranslation(Point3D translation);

    /**
     * 
     * @return the current translation
     */
    Point3D getTranslation();

    /**
     * 
     * @return the rendered segments
     */
    List<Segment2D> render();

    /**
     * 
     * @return a new MeshVisualizer
     */
    static MeshVisualizer create() {
        return new MeshVisualizerImpl();
    }
}
