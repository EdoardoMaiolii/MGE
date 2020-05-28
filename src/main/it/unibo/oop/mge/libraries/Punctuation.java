package it.unibo.oop.mge.libraries;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Punctuation implements GenericEnum{

    DOT("."), COMMA(","), OPEN_PARENTHESES("("), CLOSE_PARENTHESES(")");

    private final String name;

    private Punctuation(String name) {
        this.name = name;
    }

    public String getSyntax() {
        return this.name;
    }

    public static List<String> getSyntaxList() {
        return EnumUtilityImpl.getSyntaxList(Punctuation.class);
    }

}
