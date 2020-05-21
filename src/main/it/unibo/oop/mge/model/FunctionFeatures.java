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
     * @return get the Absolute Min of the function in the interval
     */
    Point3D getPointOfAbsoluteMin();
    /**
     * 
     * @return get the Poligonal Model of the Function in the interval
     */
    List<Segment3D> getPolygonalModel();
    /**
     * 
     * @return get the Poligonal Model of the Axis
     */
    List<Segment3D> getPoligonalAxis();
}
