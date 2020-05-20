package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;

public final class FunctionFeaturesBuilderImpl implements FunctionFeaturesBuilder {
    private Optional<AlgebricFunction> function = Optional.empty();
    private Optional<Pair<Double, Double>> interval = Optional.empty();
    private Optional<Integer> decimalPrecision = Optional.empty();
    private Optional<Double> rate = Optional.empty();
    private Optional<VariableColor> opColor = Optional.empty();
    private Optional<Color> staticColor = Optional.empty();
    private Boolean builded = false;

    private Boolean ready() {
        return function.isPresent() && interval.isPresent() && rate.isPresent() && decimalPrecision.isPresent()
                && (staticColor.isPresent() || opColor.isPresent()) && (staticColor.isEmpty() || opColor.isEmpty())
                && !builded;
    }

    @Override
    public FunctionFeaturesBuilder setFunction(final AlgebricFunction function) {
        this.function = Optional.of(function);
        return this;
    }

    @Override
    public FunctionFeaturesBuilder setIntervals(final Pair<Double, Double> interval) {
        this.interval = Optional.of(interval);
        return this;
    }

    @Override
    public FunctionFeaturesBuilder setRate(final Double rate) {
        this.rate = Optional.of(rate);
        return this;
    }

    @Override
    public FunctionFeaturesBuilder setDinamicColor(final VariableColor opColor) {
        this.opColor = Optional.of(opColor);
        return this;
    }

    @Override
    public FunctionFeaturesBuilder setDecimalPrecision(final Integer decimalPrecision) {
        this.decimalPrecision = Optional.of(decimalPrecision);
        return this;
    }

    @Override
    public FunctionFeaturesBuilder setStaticColor(final Color color) {
        this.staticColor = Optional.of(color);
        return this;
    }

    @Override
    public FunctionFeaturesImpl build() {
        if (ready()) {
            builded = true;
            return new FunctionFeaturesImpl(function.get(), interval.get(), rate.get(), opColor,
                    staticColor, decimalPrecision.get());
        }
        return null;
    }
}
