package it.unibo.oop.mge.color;

import java.util.Optional;

public class VariableColorBuilderImpl implements VariableColorBuilder {
    private Optional<Integer> red = Optional.empty();
    private Optional<Integer> green = Optional.empty();
    private Optional<Integer> blue = Optional.empty();

    @Override
    public final VariableColorBuilder setRed(final Integer red) {
        this.red = Optional.of(red);
        return this;
    }

    @Override
    public final VariableColorBuilder setGreen(final Integer green) {
        this.green = Optional.of(green);
        return this;
    }

    @Override
    public final VariableColorBuilder setBlue(final Integer blue) {
        this.blue = Optional.of(blue);
        return this;
    }

    @Override
    public final VariableColor build() {
        return new VariableColorImpl(red, green, blue);
    }
}
