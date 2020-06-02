package it.unibo.oop.mge.libraries;

import java.util.List;

/**
 * The punctuation symbols.
 */
public enum Punctuation implements GenericEnum {

    /** The dot. */
    DOT("."),

    /** The comma. */
    COMMA(","),

    /** The open parentheses. */
    OPEN_PARENTHESES("("),

    /** The close parentheses. */
    CLOSE_PARENTHESES(")");

    /** The punctuation's syntax. */
    private final String syntax;

    /**
     * Instantiates a new punctuation's syntax.
     *
     * @param syntax the punctuation's syntax
     */
    Punctuation(final String syntax) {
        this.syntax = syntax;
    }

    /**
     * Gets the syntax.
     *
     * @return the syntax
     */
    public String getSyntax() {
        return this.syntax;
    }

    /**
     * Gets the punctuation's syntaxes list.
     *
     * @return the punctuation's syntaxes list 
     */
    public static List<String> getSyntaxList() {
        return EnumUtilityImpl.getSyntaxList(Punctuation.class);
    }

}
