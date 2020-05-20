package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;

abstract class AlgebricFunctionImpl implements AlgebricFunction {
    private Types myType;
    private Optional<List<AlgebricFunction>> parameters;

    protected AlgebricFunctionImpl(final Types myType, final Optional<List<AlgebricFunction>> parameters) {
        this.myType = myType;
        this.parameters = parameters;
    }

    @Override
    public Types getType() {
        return this.myType;
    }

    public Optional<List<AlgebricFunction>> getParameters() {
        return this.parameters;
    }

    public abstract Double resolve(List<Character> pars, List<Double> values);
}
