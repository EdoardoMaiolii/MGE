package it.unibo.oop.mge.color;

public interface VariableColorBuilder {
    /**
     * 
     * @param red
     * @return a VariableColorBuilder with the parameter red setted.
     */
    VariableColorBuilder setRed(int red);

    /**
     * 
     * @param green
     * @return a VariableColorBuilder with the parameter green setted.
     */
    VariableColorBuilder setGreen(int green);

    /**
     * 
     * @param blue
     * @return a VariableColorBuilder with the parameter blue setted.
     */
    VariableColorBuilder setBlue(int blue);

    /**
     * 
     * @return a VariableColor
     */
    VariableColor build();

}
