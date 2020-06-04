package it.unibo.oop.mge.libraries;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

final class EnumUtilityImpl {

    private EnumUtilityImpl() {
    };

    private static Optional<? extends GenericEnum> getOptionalElement(final Class<? extends GenericEnum> genericEnum,
            final String syntax) {
        return Arrays.asList(genericEnum.getEnumConstants()).stream().filter(i -> i.getSyntax().equals(syntax))
                .findFirst();
    }

    static List<String> getSyntaxList(final Class<? extends GenericEnum> genericEnum) {
        return Arrays.asList(genericEnum.getEnumConstants()).stream().<String>map(i -> i.getSyntax())
                .collect(Collectors.toList());
    }

    static GenericEnum getElement(final Class<? extends GenericEnum> genericEnum, final String syntax) {
        if (enumContains(genericEnum, syntax)) {
            return getOptionalElement(genericEnum, syntax).get();
        } else {
            throw new IllegalArgumentException("Error using EnumUtility: the syntax doesn't exists");
        }
    }

    static Boolean enumContains(final Class<? extends GenericEnum> genericEnum, final String syntax) {
        return getOptionalElement(genericEnum, syntax).isPresent();
    }
}
