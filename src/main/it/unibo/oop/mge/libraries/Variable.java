package it.unibo.oop.mge.libraries;

import java.util.List;

public enum Variable implements GenericEnum {

    X("x"), Y("y");

    private final String name;

    Variable(final String name) {
        this.name = name;
    }

    public String getSyntax() {
        return this.name;
    }

    public static List<String> getListFromEnum() {
        return EnumUtilityImpl.getSyntaxList(MathFunction.class);
    }

    public static Variable getVariableFromSyntax(final String syntax) {
        return (Variable) EnumUtilityImpl.getElement(Variable.class, syntax);
    }
}
