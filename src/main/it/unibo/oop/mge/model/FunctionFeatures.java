package it.unibo.oop.mge.model;
import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;


public interface FunctionFeatures {
    /**
     * 
     * @return get the Absolute Max of the function in the interval
     */
    Point3D getPointOfAbsoluteMax();
    /**
     * 
     * @return get the Absolute minimum of the function in the interval
     */
    Point3D getPointOfAbsoluteMin();
    /**
     * 
     * @return get the polygonal Model of the Function in the interval
     */
    List<Segment3D> getPolygonalModel();
    /**
     * 
     * @return get the Segments of the 3 axis long from the minimum to the maximum value of the interval given.
     */
    List<Segment3D> getPolygonalAxis();
}
