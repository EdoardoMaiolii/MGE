package it.unibo.oop.mge.Color;

import java.awt.Color;
import java.util.function.Function;
import it.unibo.oop.mge.libraries.Pair;

public class ColorGeneratorImpl implements ColorGenerator {
    private Function<Double, Integer> linearFunction;
    private VariableColor opColor;
    private Color color;
    private TypeGenerator myType;

    public ColorGeneratorImpl(final VariableColor opColor, final Pair<Double, Integer> point1,
            final Pair<Double, Integer> point2) {
        this.myType = TypeGenerator.DINAMIC;
        this.opColor = opColor;
        final double m = (point1.getSnd() - point2.getSnd()) / (point1.getFst() - point2.getFst());
        final double q = point1.getSnd() - (m * point1.getFst());
        linearFunction = (x -> (int) (x * m + q));
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
            return new Color(opColor.getRed().isPresent()   ? opColor.getRed().get()   : linearFunction.apply(value),
                             opColor.getGreen().isPresent() ? opColor.getGreen().get() : linearFunction.apply(value),
                             opColor.getBlue().isPresent()  ? opColor.getBlue().get()  : linearFunction.apply(value));
        }
    }
}
