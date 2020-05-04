package it.unibo.oop.mge.model;
import java.util.Optional;

import it.unibo.oop.mge.function.AlgebricFunctionImpl;
import it.unibo.oop.mge.libraries.Pair;

public final class FunctionFeaturesBuilder {
    private Optional<AlgebricFunctionImpl<?>> function;
    private Optional<Pair<Double, Double>> interval;
    private Optional<Double> rate;
    private Boolean builded = false;

    private Boolean ready() {
        return function.isPresent() && interval.isPresent() && rate.isPresent() && !builded.booleanValue();
    }

    private void printError() {
        System.out.println("Error using FunctionFeaturesBuilder");
    }

    public FunctionFeaturesBuilder setFunction(final AlgebricFunctionImpl<?> function) {
        if (!builded.booleanValue() && this.function.isEmpty()) {
            this.function = Optional.of(function);
            return this;
        } else {
            printError();
            return null;
        }
    }

    public FunctionFeaturesBuilder setIntervals(final Pair<Double, Double> interval) {
        if (!builded.booleanValue() && this.interval.isEmpty()) {
            this.interval = Optional.of(interval);
            return this;
        } else {
            printError();
            return null;
        }
    }

    public FunctionFeaturesBuilder setRate(final Double rate) {
        if (!builded.booleanValue() && this.rate.isEmpty()) {
        this.rate = Optional.of(rate);
        return this;
        } else {
            printError();
            return null;
        }
    }

    public FunctionFeaturesImpl build() {
        if (ready()) {
            return new FunctionFeaturesImpl(function.get(), interval.get(), rate.get());
        } else {
            printError();
            return null;
        }
    }

}
