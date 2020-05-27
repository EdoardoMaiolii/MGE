package it.unibo.oop.mge.model;

import java.awt.Color;

import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;

public interface FunctionFeaturesBuilder {
    /**
     * 
     * @param function
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setFunction(AlgebricFunction function);

    /**
     * 
     * @param min
     * @param max
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setIntervals(double min, double max);

    /**
     * 
     * @param rate
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setRate(double rate);

    /**
     * 
     * @param varColor
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setDinamicColor(VariableColor varColor);

    /**
     * 
     * @param color
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setStaticColor(Color color);

    /**
     * 
     * @param decimalPrecision
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setDecimalPrecision(int decimalPrecision);

    /**
     * 
     * @return a FunctionFeaturesImpl with all the setting given.
     */
    FunctionFeaturesImpl build();
}
