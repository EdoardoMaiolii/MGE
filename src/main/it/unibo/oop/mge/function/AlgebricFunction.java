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
