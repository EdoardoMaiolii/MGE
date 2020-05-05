package it.unibo.oop.mge.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.mge.libraries.MathFunctions;

public abstract class AlgebricFunctionImpl<X> extends GenericFunctionImpl<X> implements AlgebricFunction<X> {
    private Map<Class<?>, Types> enummap = new HashMap<>();
    private Optional<List<AlgebricFunctionImpl<?>>> myParameters;

    protected AlgebricFunctionImpl(final X info, final Optional<List<AlgebricFunctionImpl<?>>> parameters) {
        super(info);
        enummap.put(Double.class, Types.CONSTANT);
        enummap.put(Character.class, Types.VARIABLE);
        enummap.put(MathFunctions.class, Types.MATHFUNCTION);
        myParameters = parameters;
    }

    public final Boolean isVariable() {
        return this.getType() instanceof Character;
    }

    public final Boolean isConstant() {
        return this.getType() instanceof Double;
    }

    public final Boolean isMathFunction() {
        return this.getType() instanceof MathFunctions;
    }

    public abstract Double resolve(List<Character> pars, List<Double> values);

    public final Optional<List<AlgebricFunctionImpl<?>>> getParameters() {
        return myParameters;
    }
}
