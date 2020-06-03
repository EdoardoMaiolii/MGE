package it.unibo.oop.mge.libraries;

public interface PointND {
    /**
     * 
     * @return a list of all the coordinates of the point.
     */
    /**
     * 
     * @param name
     * @return the value of the given variable.
     */
    double getVariableValue(Variable name);

    /**
     * 
     * @return the value of the function in this point.
     */
    double getFunctionValue();

    /**
     * 
     * @return a string that represent this pointND.
     */
    String toString();
}
