package it.unibo.oop.mge.libraries;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Variable {

    X("x"), Y("y");

    private final String name;

    Variable(final String name) {
        this.name = name;
    }

    public String getSyntax() {
        return this.name;
    }

    public static List<String> getListFromEnum() {
        return EnumSet.allOf(Variable.class).stream().map(i -> i.getSyntax()).collect(Collectors.toList());
    }

    public static Optional<Variable> getConstantFromSyntax(final String syntax) {
        return EnumSet.allOf(Variable.class).stream().filter(i -> i.getSyntax() == syntax).findFirst();
    }
}
