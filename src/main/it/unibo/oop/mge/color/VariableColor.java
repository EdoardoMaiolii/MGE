package it.unibo.oop.mge.color;

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
}
