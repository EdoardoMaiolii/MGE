package it.unibo.oop.mge.color;

import java.awt.Color;
import java.util.function.Function;
import it.unibo.oop.mge.libraries.Pair;

public class ColorGeneratorImpl implements ColorGenerator {
    private Function<Double, Integer> linearFunction;
    private VariableColor varColor;
    private Color color;
    private TypeGenerator myType;

    public ColorGeneratorImpl(final VariableColor varColor, final Pair<Double, Integer> point1,
            final Pair<Double, Integer> point2) {
        this.myType = TypeGenerator.DINAMIC;
        this.varColor = varColor;
        final double m = (point1.getSnd() - point2.getSnd()) / (point1.getFst() - point2.getFst());
        final double q = point1.getSnd() - (m * point1.getFst());
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
        } else {   /*myType.equals(TypeGenerator.DINAMIC)*/
            return new Color(varColor.getRed().isPresent()   ? varColor.getRed().get()   : linearFunction.apply(value),
                             varColor.getGreen().isPresent() ? varColor.getGreen().get() : linearFunction.apply(value),
                             varColor.getBlue().isPresent()  ? varColor.getBlue().get()  : linearFunction.apply(value));
        }
    }
}
