package it.unibo.oop.mge.color;

import java.awt.Color;
import java.util.function.Function;

public class ColorGeneratorImpl implements ColorGenerator {
    private Function<Double, Integer> linearFunction;
    private Function<Double, Color> colorFunction;
    private static final int MAXRGBVALUE = 255;
    private static final int MINRGBVALUE = 0;

    public ColorGeneratorImpl(final VariableColor varColor, final double min, final double max) {
        final double m = MAXRGBVALUE / (min - max);
        final double q = m * MINRGBVALUE;
        linearFunction = (x -> (int) (m * x + q));
        colorFunction = i -> {
            i = i > max ? max : i < min ? min : i;
            return new Color(varColor.getRed().isPresent() ? varColor.getRed().get() : linearFunction.apply(i),
                    varColor.getGreen().isPresent() ? varColor.getGreen().get() : linearFunction.apply(i),
                    varColor.getBlue().isPresent() ? varColor.getBlue().get() : linearFunction.apply(i));
        };
    }

    public ColorGeneratorImpl(final Color color) {
        this.colorFunction = i -> color;
    }

    @Override
    public final Color getColorFromDouble(final Double value) {
        return this.colorFunction.apply(value);
    }
}
