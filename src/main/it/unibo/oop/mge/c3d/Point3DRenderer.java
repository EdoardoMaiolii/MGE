package it.unibo.oop.mge.c3d;

import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;

public interface Point3DRenderer {

    /**
     * 
     * @param point the source point
     * @return a new Point3DRenderer
     */
    static Point3DRenderer fromPoint(Point3D point) {
        return new Point3DRendererImpl(point);
    }

    /**
     * 
     * @param pointOfView the point of view for the perspective calculation
     * @return the rendered point
     */
    Point2D render(Point2D pointOfView);
}
