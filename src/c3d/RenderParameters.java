package c3d;

import c3d.geometry.Point2D;
import c3d.geometry.Point3D;

public interface RenderParameters {
    /**
     * 
     * @return the angle on the XY axis
     */
    double rotationXY();

    /**
     * 
     * @return the angle on the YZ axis
     */
    double rotationYZ();

    /**
     * 
     * @return the translation
     */
    Point3D translation();

    /**
     * 
     * @return the point of view for the perspective calculation
     */
    Point2D pointOfView();
}
