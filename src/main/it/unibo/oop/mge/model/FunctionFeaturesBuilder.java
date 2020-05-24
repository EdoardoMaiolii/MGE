package it.unibo.oop.mge.model;

import java.awt.Color;

import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;

public interface FunctionFeaturesBuilder {

    FunctionFeaturesBuilder setFunction(AlgebricFunction function);

    FunctionFeaturesBuilder setIntervals(double min, double max);

    FunctionFeaturesBuilder setRate(double rate);

    FunctionFeaturesBuilder setDinamicColor(VariableColor varColor);

    FunctionFeaturesBuilder setStaticColor(Color color);

    FunctionFeaturesBuilder setDecimalPrecision(int decimalPrecision);

    FunctionFeaturesImpl build();
}
