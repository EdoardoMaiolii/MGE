package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.List;

import it.unibo.oop.mge.Color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;

public interface FunctionFeaturesBuilder {

    FunctionFeaturesBuilder setFunction(AlgebricFunction function);

    FunctionFeaturesBuilder setIntervals(Pair<Double, Double> interval);

    FunctionFeaturesBuilder setRate(Double rate);

    FunctionFeaturesBuilder setVariables(List<Character> variables);

    FunctionFeaturesBuilder setDinamicColor(VariableColor opColor);

    FunctionFeaturesBuilder setStaticColor(Color color);

    FunctionFeaturesBuilder setDecimalPrecision(Integer decimalPrecision);

    FunctionFeaturesImpl build();
}
