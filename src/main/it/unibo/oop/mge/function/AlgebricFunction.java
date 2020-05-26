package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.mge.libraries.Variable;


public interface AlgebricFunction {

    /**
     * 
     * @param values
     * @return the value of the function.
     */
    Double resolve(Map<Variable, Double> values);

    /**
     * 
     * @return an optional that could contains the parameters.
     */
    Optional<List<AlgebricFunction>> getParameters();
}
