package it.unibo.oop.mge.color;

import java.util.Optional;

public class VariableColorImpl implements VariableColor {
    private Optional<Integer> red, green, blue;

    protected VariableColorImpl(final Optional<Integer> red, final Optional<Integer> green,
            final Optional<Integer> blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public final Optional<Integer> getRed() {
        return red;
    }

    @Override
    public final Optional<Integer> getGreen() {
        return green;
    }

    @Override
    public final Optional<Integer> getBlue() {
        return blue;
    }

    public final Boolean isEmpty() {
        return this.getRed().isEmpty() && this.getGreen().isEmpty() && this.getBlue().isEmpty();
    }

    public final Boolean isPresent() {
        return !this.isEmpty();
    }
}
