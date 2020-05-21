package it.unibo.oop.mge.model;

import it.unibo.oop.mge.function.AlgebricFunction;

public interface FunctionParser {
    static AlgebricFunction parse(final String fstring) {
        return FunctionParserImpl.parseString(fstring);
    }
}
