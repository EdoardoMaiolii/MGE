package it.unibo.oop.mge.model;
import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Segment3D;
import it.unibo.oop.mge.libraries.PointND;

public interface FunctionFeatures {
    /**
     * 
     * @return get the points belong to the domain
     */
    List<PointND> getRealPoints();
    /**
     * 
     * @return get the points that not belong to the domain
     */
    List<PointND> getImmaginaryPoints();
    /**
     * 
     * @return get the Absolute Max of the function in the interval
     */
    PointND getPointOfAbsoluteMax();
    /**
     * 
     * @return get the Absolute Min of the function in the interval
     */
    PointND getPointOfAbsoluteMin();
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
