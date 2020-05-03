package model;

public interface FunctionParser {
	
	public static AlgebricFunction<?> parse(String fstring) {
		return FunctionParserImpl.parseString(fstring);
	}
}
