package it.unibo.oop.mge.parser;

import it.unibo.oop.mge.libraries.BracketsUtility;

public class CheckBrackets extends StringDecorator {
    public CheckBrackets(final Object str) {
        super(str);
    }

    @Override
    protected final String stringParser(final String str) {
        if (!BracketsUtility.checkBrackets(str)) {
            throw new java.lang.IllegalArgumentException();
        } else {
            return str;
        }
    }

}
