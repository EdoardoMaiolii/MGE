package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.Optional;

import it.unibo.oop.mge.Color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;

public final class FunctionFeaturesBuilderImpl implements FunctionFeaturesBuilder {
    private Optional<AlgebricFunction<?>> function = Optional.empty();
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

    private void printError() {
        System.out.println("Error using FunctionFeaturesBuilder");
    }

    @Override
    public FunctionFeaturesBuilder setFunction(final AlgebricFunction<?> function) {
        if (!builded && this.function.isEmpty()) {
            this.function = Optional.of(function);
            return this;
        } else {
            printError();
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setIntervals(final Pair<Double, Double> interval) {
        if (!builded && this.interval.isEmpty()) {
            this.interval = Optional.of(interval);
            return this;
        } else {
            printError();
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setRate(final Double rate) {
        if (!builded && this.rate.isEmpty()) {
            this.rate = Optional.of(rate);
            return this;
        } else {
            printError();
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setDinamicColor(final VariableColor opColor) {
        if (!builded && this.opColor.isEmpty()) {
            this.opColor = Optional.of(opColor);
            return this;
        } else {
            printError();
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setDecimalPrecision(final Integer decimalPrecision) {
        if (!builded && this.decimalPrecision.isEmpty()) {
            this.decimalPrecision = Optional.of(decimalPrecision);
            return this;
        } else {
            printError();
            return null;
        }
    }

    @Override
    public FunctionFeaturesBuilder setStaticColor(final Color color) {
        if (!builded && this.staticColor.isEmpty()) {
            this.staticColor = Optional.of(color);
            return this;
        } else {
            printError();
            return null;
        }
    }

    @Override
    public FunctionFeaturesImpl build() {
        if (ready()) {
            builded = true;
            return new FunctionFeaturesImpl(function.get(), interval.get(), rate.get(), opColor, staticColor,
                    decimalPrecision.get());
        } else {
            printError();
            return null;
        }
    }
}
