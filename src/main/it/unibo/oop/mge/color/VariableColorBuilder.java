package it.unibo.oop.mge.color;

public interface VariableColorBuilder {
    /**
     * 
     * @param red
     * @return a VariableColorBuilder
     */
    VariableColorBuilder setRed(Integer red);
    /**
     * 
     * @param green
     * @return a VariableColorBuilder
     */
    VariableColorBuilder setGreen(Integer green);
    /**
     * 
     * @param blue
     * @return a VariableColorBuilder
     */
    VariableColorBuilder setBlue(Integer blue);
    /**
     * 
     * @return a VariableColor
     */
    VariableColor build();

}
