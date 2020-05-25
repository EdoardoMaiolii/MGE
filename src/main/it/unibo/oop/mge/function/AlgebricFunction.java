package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;


public interface AlgebricFunction {

    /**
     * 
     * @param xValue
     * @param yValue
     * @return the value of the function.
     */
    Double resolve(double xValue, double yValue);

    /**
     * 
     * @return an optional that could contains the parameters.
     */
    Optional<List<AlgebricFunction>> getParameters();
}
