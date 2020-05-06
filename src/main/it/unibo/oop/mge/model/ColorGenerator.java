package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.optionalColor.OptionalColor;

public class ColorGenerator {
    private Function<Double, Integer> lineFrom2Points;
    private OptionalColor opColor;
    private Color color;
    private TypeGenerator myType;

    enum TypeGenerator {
        STATIC, DINAMIC,
    }

    public ColorGenerator(final OptionalColor opColor, final Pair<Double, Integer> point1,
            final Pair<Double, Integer> point2) {
        this.myType = TypeGenerator.DINAMIC;
        this.opColor = opColor;
        final double m = (point1.getSnd() - point2.getSnd()) / (point1.getFst() - point2.getFst());
        final double q = point1.getSnd() - (m * point1.getFst());
        lineFrom2Points = (x -> (int) (x * m + q));
    }

    public ColorGenerator(final Color color) {
        this.myType = TypeGenerator.STATIC;
        this.color = color;
    }

    public final Color getColorFromDouble(final Double value) {
        if (myType.equals(TypeGenerator.STATIC)) {
            return color;
        } else {
            return new Color(opColor.getRed().isPresent() ? opColor.getRed().get() : lineFrom2Points.apply(value),
                    opColor.getGreen().isPresent() ? opColor.getGreen().get() : lineFrom2Points.apply(value),
                    opColor.getBlue().isPresent() ? opColor.getBlue().get() : lineFrom2Points.apply(value));
        }
    }
}
