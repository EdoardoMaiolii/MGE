package it.unibo.oop.mge.optionalColor;

public interface OptionalColorBuilder {

    OptionalColorBuilder setRed(Integer red);

    OptionalColorBuilder setGreen(Integer green);

    OptionalColorBuilder setBlue(Integer blue);

    OptionalColor build();

}
