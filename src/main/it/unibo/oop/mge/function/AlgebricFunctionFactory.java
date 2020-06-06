package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Variable;
import it.unibo.oop.mge.libraries.Constant;

/**
 * A factory for creating AlgebricFunction objects.
 */
public interface AlgebricFunctionFactory {

    /**
     * Gets the value function.
     *
     * @param value is the Number
     * @return an AlgebricFunction that correspond to a Number
     */
    static AlgebricFunction getValueFunction(Double value) {
        return new AlgebricFunctionImpl(Optional.empty()) {
            @Override
            public Double resolve(final Map<Variable, Double> values) {
                return value;
            }
        };
    }

    /**
     * Gets the constant function.
     *
     * @param c is the constant
     * @return an AlgebricFunction that correspond to the value of the constant
     */
    static AlgebricFunction getConstantFunction(final Constant c) {
        return getValueFunction(c.resolve());
    }

    /**
     * Gets the parameter function.
     *
     * @param name is the name if the variable
     * @return an AlgebricFunction that correspond to a variable
     */
    static AlgebricFunction getParameterFunction(final Variable name) {
        return new AlgebricFunctionImpl(Optional.empty()) {
            @Override
            public Double resolve(final Map<Variable, Double> values) {
                return values.get(name);
            }
        };
    }

    /**
     * Gets the math function.
     *
     * @param id   is the type of the Function
     * @param pars is the parameters of the Function
     * @return an AlgebricFunction that is a Mathematical Function
     */
    static AlgebricFunction getMathFunction(final MathFunction id, final List<AlgebricFunction> pars) {
        return new AlgebricFunctionImpl(Optional.of(pars)) {
            @Override
            public Double resolve(final Map<Variable, Double> values) {
                return id.resolve(
                        this.getParameters().get().stream().map(i -> i.resolve(values)).collect(Collectors.toList()));
            }
        };
    }
}
