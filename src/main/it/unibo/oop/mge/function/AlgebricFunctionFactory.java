package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.oop.mge.function.AlgebricFunction.Types;
import it.unibo.oop.mge.libraries.Constants;
import it.unibo.oop.mge.libraries.MathFunctions;

public interface AlgebricFunctionFactory {
    /**
     * 
     * @param value is the Number
     * @return an AlgebricFunction that correspond to a Number
     */
    static AlgebricFunction<Double> getValueFunction(Double value) {
        return new AlgebricFunctionImpl<Double>(value, Optional.empty(), Types.CONSTANT) {
            @Override
            public Double resolve(final List<Character> pars, final List<Double> values) {
                return this.getType();
            }
        };
    }

    /**
     * 
     * @param c is the constant
     * @return an AlgebricFunction that correspond to the value of the constant
     */
    static AlgebricFunction<Double> getConstantFunction(final Constants c) {
        return getValueFunction(c.resolve());
    }

    /**
     * 
     * @param name is the name if the variable
     * @return an AlgebricFunction that correspond to a variable
     */
    static AlgebricFunction<Character> getParameterFunction(Character name) {
        return new AlgebricFunctionImpl<Character>(name, Optional.empty(), Types.VARIABLE) {
            @Override
            public Double resolve(final List<Character> pars, final List<Double> values) {
                return values.get(pars.indexOf(this.getType()));
            }
        };
    }

    /**
     * 
     * @param id   is the type of the Function
     * @param pars is the parameters of the Function
     * @return an AlgebricFunction that is a Mathematical Function
     */
    static AlgebricFunction<MathFunctions> getMathFunction(MathFunctions id, List<AlgebricFunction<?>> pars) {
        if (id.getNParameters() != pars.size()) {
            System.out.println(
                    "Error parsing the function , wrong number of parameters of the function:" + id.getSyntax());
            return null;
        }
        return new AlgebricFunctionImpl<MathFunctions>(id, Optional.of(pars), Types.MATHFUNCTION) {
            @Override
            public Double resolve(final List<Character> pars, final List<Double> values) {
                return this.getType().resolve(this.getParameters().get().stream().map(i -> i.resolve(pars, values))
                        .collect(Collectors.toList()));
            }
        };
    }
}
