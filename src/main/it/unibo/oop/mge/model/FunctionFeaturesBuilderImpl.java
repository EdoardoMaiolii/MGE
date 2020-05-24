package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.Optional;

import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;

public final class FunctionFeaturesBuilderImpl implements FunctionFeaturesBuilder {
    private Optional<AlgebricFunction> function = Optional.empty();
    private Optional<Pair<Double, Double>> interval = Optional.empty();
    private Optional<Integer> decimalPrecision = Optional.empty();
    private Optional<Double> rate = Optional.empty();
    private Optional<VariableColor> varColor = Optional.empty();
    private Optional<Color> staticColor = Optional.empty();
    private Boolean builded = false;

    private Boolean isNull(final Object obj) {
        return obj == null ? true : false;
    }

    private Boolean ready() {
        return this.function.isPresent() && this.interval.isPresent() && this.rate.isPresent() && this.decimalPrecision.isPresent()
                && (this.staticColor.isPresent() || this.varColor.isPresent()) && (this.staticColor.isEmpty() || this.varColor.isEmpty())
                && !this.builded;
    }

    @Override
    public FunctionFeaturesBuilder setFunction(final AlgebricFunction function) {
        if (this.function.isEmpty() && !isNull(function)) {
            this.function = Optional.of(function);
            return this;
        } else {
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setIntervals(final double min, final double max) {
        if (this.interval.isEmpty() && min < max) {
            this.interval = Optional.of(new Pair<Double, Double>(min, max));
            return this;
        } else {
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setRate(final double rate) {
        if (this.rate.isEmpty() && rate > 0) {
            this.rate = Optional.of(rate);
            return this;
        } else {
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setDinamicColor(final VariableColor varColor) {
        if (this.varColor.isEmpty() && this.staticColor.isEmpty() && !isNull(varColor)) {
            this.varColor = Optional.of(varColor);
            return this;
        } else {
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setDecimalPrecision(final int decimalPrecision) {
        if (this.decimalPrecision.isEmpty() && decimalPrecision > 0) {
            this.decimalPrecision = Optional.of(decimalPrecision);
            return this;
        } else {
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setStaticColor(final Color color) {
        if (this.staticColor.isEmpty() && this.varColor.isEmpty() && !isNull(color)) {
            this.staticColor = Optional.of(color);
            return this;
        } else {
            return null;
        }
    }

    @Override
    public FunctionFeaturesImpl build() {
        if (this.ready()) {
            this.builded = true;
            return new FunctionFeaturesImpl(this.function.get(), this.interval.get(), this.rate.get(), this.varColor,
                    this.staticColor, this.decimalPrecision.get());
        } else {
            return null;
        }
    }
}
