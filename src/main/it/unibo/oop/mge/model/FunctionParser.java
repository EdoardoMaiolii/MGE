package it.unibo.oop.mge.model;

import it.unibo.oop.mge.function.AlgebricFunctionImpl;

public interface FunctionParser {
	
	public static AlgebricFunctionImpl<?> parse(String fstring) {
		return FunctionParserImpl.parseString(fstring);
	}
}
