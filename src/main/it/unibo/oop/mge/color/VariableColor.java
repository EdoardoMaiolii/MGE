package it.unibo.oop.mge.color;

import java.util.Optional;

public interface VariableColor {
    /**
     * 
     * @return an optional containing: the red parameter of the VariableColor or
     *         Optional.empty().
     */
    Optional<Integer> getRed();

    /**
     * 
     * @return an optional containing: the green parameter of the VariableColor or
     *         Optional.empty().
     */
    Optional<Integer> getGreen();

    /**
     * 
     * @return an optional containing: the blue parameter of the VariableColor or
     *         Optional.empty().
     */
    Optional<Integer> getBlue();
}
