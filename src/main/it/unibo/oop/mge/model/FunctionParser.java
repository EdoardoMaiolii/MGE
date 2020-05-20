package it.unibo.oop.mge.model;

import it.unibo.oop.mge.function.AlgebricFunction;

public interface FunctionParser {
	
	public static AlgebricFunction parse(String fstring) {
		return FunctionParserImpl.parseString(fstring);
	}
}
