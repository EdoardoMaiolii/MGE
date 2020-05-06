package it.unibo.oop.mge.optionalColor;

import java.util.Optional;

public class OptionalColorBuilderImpl implements OptionalColorBuilder {
    private Optional<Integer> red = Optional.empty();
    private Optional<Integer> green = Optional.empty();
    private Optional<Integer> blue = Optional.empty();

    private void printError() {
        System.out.println("Error Using OptionalColorBuilder");
    }

    @Override
    public final OptionalColorBuilder setRed(final Integer red) {
        if (this.red.isPresent()) {
            printError();
            return null;
        } else {
            this.red = Optional.of(red);
            return this;
        }
    }

    @Override
    public final OptionalColorBuilder setGreen(final Integer green) {
        if (this.green.isPresent()) {
            printError();
            return null;
        } else {
            this.green = Optional.of(green);
            return this;
        }
    }

    @Override
    public final OptionalColorBuilder setBlue(final Integer blue) {
        if (this.blue.isPresent()) {
            printError();
            return null;
        } else {
            this.blue = Optional.of(blue);
            return this;
        }
    }

    @Override
    public final OptionalColor build() {
        return new OptionalColorImpl(red, green, blue);
    }
}
