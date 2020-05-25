package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Variable;
import it.unibo.oop.mge.libraries.Constant;

public interface AlgebricFunctionFactory {
    /**
     * 
     * @param value is the Number
     * @return an AlgebricFunction that correspond to a Number
     */
    static AlgebricFunction getValueFunction(Double value) {
        return new AlgebricFunctionImpl(Optional.empty()) {
            @Override
            public Double resolve(final double xValue, final double yValue) {
                return value;
            }
        };
    }

    /**
     * 
     * @param c is the constant
     * @return an AlgebricFunction that correspond to the value of the constant
     */
    static AlgebricFunction getConstantFunction(final Constant c) {
        return getValueFunction(c.resolve());
    }

    /**
     * 
     * @param name is the name if the variable
     * @return an AlgebricFunction that correspond to a variable
     */
    static AlgebricFunction getParameterFunction(final Variable name) {
        return new AlgebricFunctionImpl(Optional.empty()) {
            @Override
            public Double resolve(final double xValue, final double yValue) {
                return name.equals(Variable.X) ? xValue : yValue;
            }
        };
    }

    /**
     * 
     * @param id   is the type of the Function
     * @param pars is the parameters of the Function
     * @return an AlgebricFunction that is a Mathematical Function
     */
    static AlgebricFunction getMathFunction(final MathFunction id, final List<AlgebricFunction> pars) {
        return new AlgebricFunctionImpl(Optional.of(pars)) {
            @Override
            public Double resolve(final double xValue, final double yValue) {
                return id.resolve(this.getParameters().get().stream().map(i -> i.resolve(xValue, yValue))
                        .collect(Collectors.toList()));
            }
        };
    }
}
