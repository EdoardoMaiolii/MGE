package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;

public interface AlgebricFunction {
    enum Types {
        CONSTANT, VARIABLE, MATHFUNCTION;
    }
    /**
     * 
     * @return the type if the AlgebricFunction.
     */
    Types getType();

    /**
     * 
     * @return true if the function is a Variable Function.
     */
    default Boolean isVariable() {
        return this.getType().equals(Types.VARIABLE);
    }

    /**
     * 
     * @return true if the function is a Mathematical Constant Function.
     */
    default Boolean isConstant() {
        return this.getType().equals(Types.CONSTANT);
    }

    /**
     * 
     * @return true if the function is a Mathematical Function.
     */
    default Boolean isMathFunction() {
        return this.getType().equals(Types.MATHFUNCTION);
    }

    /**
     * 
     * @param pars   are the names of the variables
     * @param values are the values if the variables
     * @return the value of the function.
     */
    Double resolve(List<Character> pars, List<Double> values);

    /**
     * 
     * @return an optional that could contains the parameters.
     */
    Optional<List<AlgebricFunction>> getParameters();
}
