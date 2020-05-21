package it.unibo.oop.mge.color;

import java.awt.Color;

public interface ColorGenerator {

    enum TypeGenerator {
        STATIC, DINAMIC,
    }
    /**
     * 
     * @param value
     * @return the color associated to the given value
     *         colors are linearly allocated.
     */
    Color getColorFromDouble(Double value);

}
