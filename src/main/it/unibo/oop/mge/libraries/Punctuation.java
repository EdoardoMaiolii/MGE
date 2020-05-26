package it.unibo.oop.mge.libraries;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Punctuation {

    DOT("."), COMMA(","), OPEN_PARENTHESES("("), CLOSE_PARENTHESES(")");

    private final String name;

    private Punctuation(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    protected static List<String> getListFromEnum() {
        return Arrays.asList(Punctuation.values()).stream().map(e -> e.getName()).collect(Collectors.toList());
    }
}
