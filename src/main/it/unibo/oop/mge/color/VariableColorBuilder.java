package it.unibo.oop.mge.color;

/**
 * The Interface VariableColorBuilder.
 */
public interface VariableColorBuilder {

    /**
     * Sets the red.
     *
     * @param red value.
     * @return a VariableColorBuilder with the parameter red setted.
     */
    VariableColorBuilder setRed(int red);

    /**
     * Sets the green.
     *
     * @param green value.
     * @return a VariableColorBuilder with the parameter green setted.
     */
    VariableColorBuilder setGreen(int green);

    /**
     * Sets the blue.
     *
     * @param blue value.
     * @return a VariableColorBuilder with the parameter blue setted.
     */
    VariableColorBuilder setBlue(int blue);

    /**
     * Builds the VariableColor.
     *
     * @return a VariableColor.
     */
    VariableColor build();

}
