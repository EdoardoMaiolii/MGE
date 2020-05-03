package model;

import function.AlgebricFunctionImpl;

public interface FinalParser {
    AlgebricFunctionImpl<?> resolveFunction(String fstring);
}
