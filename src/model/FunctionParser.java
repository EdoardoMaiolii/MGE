package model;

import function.AlgebricFunctionImpl;

public interface FunctionParser {
	
	public static AlgebricFunctionImpl<?> parse(String fstring) {
		return FunctionParserImpl.parseString(fstring);
	}
}
