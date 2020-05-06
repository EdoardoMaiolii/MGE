package it.unibo.oop.mge.model;

import java.awt.Color;

import it.unibo.oop.mge.function.AlgebricFunctionImpl;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.optionalColor.OptionalColor;

public interface FunctionFeaturesBuilder {

    FunctionFeaturesBuilder setFunction(AlgebricFunctionImpl<?> function);

    FunctionFeaturesBuilder setIntervals(Pair<Double, Double> interval);

    FunctionFeaturesBuilder setRate(Double rate);

    FunctionFeaturesBuilder setDinamicColor(OptionalColor opColor);

    FunctionFeaturesBuilder setStaticColor(Color color);

    FunctionFeaturesBuilder setDecimalPrecision(Integer decimalPrecision);

    FunctionFeaturesImpl build();
}
