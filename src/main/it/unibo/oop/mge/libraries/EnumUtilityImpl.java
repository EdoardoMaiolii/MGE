package it.unibo.oop.mge.libraries;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class EnumUtilityImpl {

    private EnumUtilityImpl() {
    };

    private static void throwIllArgExc() {
        throw new IllegalArgumentException("Error using EnumUtility");
    }

    private static Optional<? extends GenericEnum> getOptionalElement(final Class<? extends GenericEnum> genericEnum,
            final String syntax) {
        if (genericEnum.isEnum()) {
            return Arrays.asList(genericEnum.getEnumConstants()).stream().filter(i -> i.getSyntax() == syntax)
                    .findFirst();
        } else {
            throwIllArgExc();
        }
        return null;
    }

    public static List<String> getSyntaxList(final Class<? extends GenericEnum> genericEnum) {
        if (genericEnum.isEnum()) {
            return Arrays.asList(genericEnum.getEnumConstants()).stream().<String>map(i -> i.getSyntax())
                    .collect(Collectors.toList());
        } else {
            throwIllArgExc();
            return null;
        }
    }

    public static GenericEnum getElement(final Class<? extends GenericEnum> genericEnum, final String syntax) {
        if (enumContains(genericEnum, syntax)) {
            return getOptionalElement(genericEnum, syntax).get();
        } else {
            throwIllArgExc();
            return null;
        }
    }

    public static Boolean enumContains(final Class<? extends GenericEnum> genericEnum, final String syntax) {
        return getOptionalElement(genericEnum, syntax).isPresent();

    }
}
