package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;

abstract class AlgebricFunctionImpl<X> extends GenericFunctionImpl<X> implements AlgebricFunction<X> {
    private Optional<List<AlgebricFunctionImpl<?>>> myParameters;
    private Types myType;

    protected AlgebricFunctionImpl(final X info, final Optional<List<AlgebricFunctionImpl<?>>> parameters,
            final Types myType) {
        super(info);
        this.myParameters = parameters;
        this.myType = myType;
    }

    public final Types myType() {
        return this.myType;
    }

    public abstract Double resolve(List<Character> pars, List<Double> values);

    public final Optional<List<AlgebricFunctionImpl<?>>> getParameters() {
        return myParameters;
    }
}
