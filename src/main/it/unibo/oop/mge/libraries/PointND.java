package it.unibo.oop.mge.libraries;

import java.util.List;

public interface PointND {
    /**
     * 
     * @return a list of all the coordinates of the point.
     */
    List<Double> getValues();

    /**
     * 
     * @return a string that represent this pointND.
     */
    String toString();
}
