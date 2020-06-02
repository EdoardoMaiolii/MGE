package it.unibo.oop.mge.libraries;

import java.util.List;

/**
 * The variables of a math function.
 */
public enum Variable implements GenericEnum {

    /** The x variable. */
    X("x"), 

    /** The y variable. */
    Y("y");

    /** The variable's syntax. */
    private final String syntax;

    /**
     * Instantiates a new variable.
     *
     * @param syntax the variable's syntax
     */
    Variable(final String syntax) {
        this.syntax = syntax;
    }

    /**
     * Gets the variable's syntax.
     *
     * @return the variables's syntax
     */
    public String getSyntax() {
        return this.syntax;
    }

    /**
     * Gets the variables syntax list.
     *
     * @return the variables syntax list
     */
    public static List<String> getSyntaxList() {
        return EnumUtilityImpl.getSyntaxList(Variable.class);
    }

    /**
     * Gets the variable from syntax.
     *
     * @param syntax the syntax
     * @return the variable from syntax
     */
    public static Variable getVariableFromSyntax(final Character syntax) {
        return (Variable) EnumUtilityImpl.getElement(Variable.class, String.valueOf(syntax));
    }
}
