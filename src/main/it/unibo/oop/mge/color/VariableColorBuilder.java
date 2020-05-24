package it.unibo.oop.mge.color;

public interface VariableColorBuilder {
    /**
     * 
     * @param red
     * @return a VariableColorBuilder
     */
    VariableColorBuilder setRed(int red);
    /**
     * 
     * @param green
     * @return a VariableColorBuilder
     */
    VariableColorBuilder setGreen(int green);
    /**
     * 
     * @param blue
     * @return a VariableColorBuilder
     */
    VariableColorBuilder setBlue(int blue);
    /**
     * 
     * @return a VariableColor
     */
    VariableColor build();

}
