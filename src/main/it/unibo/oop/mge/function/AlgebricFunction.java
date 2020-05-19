package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;

public interface AlgebricFunction<X> extends GenericFunction<X> {
    enum Types {
        CONSTANT, VARIABLE, MATHFUNCTION;
    }
    /**
     * 
     * @return the type if this AlgebricFunction.
     */
    Types myType();
    /**
     * 
     * @return true if the function is a Variable Function.
     */
    default Boolean isVariable() {
        return this.myType().equals(Types.VARIABLE);
    }

    /**
     * 
     * @return true if the function is a Mathematical Constant Function.
     */
    default Boolean isConstant() {
        return this.myType().equals(Types.CONSTANT);
    }

    /**
     * 
     * @return true if the function is a Mathematical Function.
     */
    default Boolean isMathFunction() {
        return this.myType().equals(Types.MATHFUNCTION);
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
    Optional<List<AlgebricFunction<?>>> getParameters();
}
