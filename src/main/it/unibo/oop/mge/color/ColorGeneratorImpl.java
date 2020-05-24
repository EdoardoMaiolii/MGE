package it.unibo.oop.mge.color;

import java.awt.Color;
import java.util.function.Function;

public class ColorGeneratorImpl implements ColorGenerator {
    private Function<Double, Integer> linearFunction;
    private VariableColor varColor;
    private Color color;
    private TypeGenerator myType;
    private static final int MAXRGBVALUE = 255;
    private static final int MINRGBVALUE = 0;

    public ColorGeneratorImpl(final VariableColor varColor, final double min, final double max) {
        this.myType = TypeGenerator.DINAMIC;
        this.varColor = varColor;
        final double m = MAXRGBVALUE / (min - max);
        final double q = m * MINRGBVALUE;
        linearFunction = (x -> (int) (m * x + q));
    }

    public ColorGeneratorImpl(final Color color) {
        this.myType = TypeGenerator.STATIC;
        this.color = color;
    }

    @Override
    public final Color getColorFromDouble(final Double value) {
        if (myType.equals(TypeGenerator.STATIC)) {
            return color;
        } else { /* myType.equals(TypeGenerator.DINAMIC) */
            int colorValue;
            /* If the number is greater then 255 i set 255 */
            if (linearFunction.apply(value) < MINRGBVALUE) {
                colorValue = MINRGBVALUE;
                /* If the number is lower then 0 i set 0 */
            } else if (linearFunction.apply(value) > MAXRGBVALUE) {
                colorValue = MAXRGBVALUE;
                /*
                 * If the number belong to the interval i use the function to get the right
                 * value
                 */
            } else {
                colorValue = linearFunction.apply(value);
            }
            return new Color(varColor.getRed().isPresent() ? varColor.getRed().get() : colorValue,
                    varColor.getGreen().isPresent() ? varColor.getGreen().get() : colorValue,
                    varColor.getBlue().isPresent() ? varColor.getBlue().get() : colorValue);
        }
    }
}
