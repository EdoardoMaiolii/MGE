package it.unibo.oop.mge.Color;

import java.util.Optional;

public interface VariableColor {
    /**
     * 
     * @return an optional of the red parameter of the color created
     */
    Optional<Integer> getRed();
    /**
     * 
     * @return an optional of the green parameter of the color created
     */
    Optional<Integer> getGreen();
    /**
     * 
     * @return an optional of the blue parameter of the color created
     */
    Optional<Integer> getBlue();
    /**
     * 
     * @return true if all the parameters (red,green,blue) are unsetted, false otherwise
     */
    Boolean isEmpty();
    /**
     * 
     * @return true if all the parameters are setted, false otherwise
     */
    Boolean isPresent();
}
