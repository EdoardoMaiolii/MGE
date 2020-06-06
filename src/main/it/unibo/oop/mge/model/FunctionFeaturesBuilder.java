package it.unibo.oop.mge.model;

import java.awt.Color;

import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;

/**
 * The Interface FunctionFeaturesBuilder.
 */
public interface FunctionFeaturesBuilder {

    /**
     * Sets the function.
     *
     * @param function the function
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setFunction(AlgebricFunction function);

    /**
     * Sets the intervals.
     *
     * @param min the min
     * @param max the max
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setIntervals(double min, double max);

    /**
     * Sets the rate.
     *
     * @param rate the rate
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setRate(double rate);

    /**
     * Sets the dinamic color.
     *
     * @param varColor the var color
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setDinamicColor(VariableColor varColor);

    /**
     * Sets the static color.
     *
     * @param color the color
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setStaticColor(Color color);

    /**
     * Sets the decimal precision.
     *
     * @param decimalPrecision the decimal precision
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder setDecimalPrecision(int decimalPrecision);

    /**
     * Builds the FunctionFeatures object.
     *
     * @return a FunctionFeaturesImpl with all the setting given.
     */
    FunctionFeaturesImpl build();
}
