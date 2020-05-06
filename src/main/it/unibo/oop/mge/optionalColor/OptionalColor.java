package it.unibo.oop.mge.optionalColor;

import java.util.Optional;

public interface OptionalColor {

    Optional<Integer> getRed();

    Optional<Integer> getGreen();

    Optional<Integer> getBlue();

    Boolean isEmpty();

    Boolean isPresent();
}
