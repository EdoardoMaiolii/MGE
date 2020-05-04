package it.unibo.oop.mge.model;
import java.util.Optional;

import it.unibo.oop.mge.function.AlgebricFunctionImpl;
import it.unibo.oop.mge.libraries.Pair;

public final class FunctionFeaturesBuilder {
    private Optional<AlgebricFunctionImpl<?>> function = Optional.empty();
    private Optional<Pair<Double, Double>> interval = Optional.empty();
    private Optional<Double> rate = Optional.empty();
    private Boolean builded = false;

    private Boolean ready() {
        return function.isPresent() && interval.isPresent() && rate.isPresent() && !builded;
    }

    private void printError() {
        System.out.println("Error using FunctionFeaturesBuilder");
    }

    public FunctionFeaturesBuilder setFunction(final AlgebricFunctionImpl<?> function) {
        if (!builded && this.function.isEmpty()) {
            this.function = Optional.of(function);
            return this;
        } else {
            printError();
            return null;
        }
    }

    public FunctionFeaturesBuilder setIntervals(final Pair<Double, Double> interval) {
        if (!builded && this.interval.isEmpty()) {
            this.interval = Optional.of(interval);
            return this;
        } else {
            printError();
            return null;
        }
    }

    public FunctionFeaturesBuilder setRate(final Double rate) {
        if (!builded && this.rate.isEmpty()) {
        this.rate = Optional.of(rate);
        return this;
        } else {
            printError();
            return null;
        }
    }

    public FunctionFeaturesImpl build() {
        if (ready()) {
            builded = true;
            return new FunctionFeaturesImpl(function.get(), interval.get(), rate.get());
        } else {
            printError();
            return null;
        }
    }

}
