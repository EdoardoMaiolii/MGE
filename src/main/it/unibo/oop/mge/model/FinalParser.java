package it.unibo.oop.mge.model;

import it.unibo.oop.mge.function.AlgebricFunctionImpl;

public interface FinalParser {
    AlgebricFunctionImpl<?> resolveFunction(String fstring);
}
