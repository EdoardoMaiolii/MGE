package model;

public class FunctionParserImpl implements FunctionParser{
	
	private static BracketsParser peeledString;
	private static FinalParser func = new FinalParserImpl();
	
	private FunctionParserImpl() {}
	
	protected static AlgebricFunction<?> parseString(String str) {
		peeledString = new BracketsParserImpl(str);
		//System.out.println(peeledString.resolveBrackets()); //decommentare per vedere la stringa riscritta stampata
		return func.resolveFunction(peeledString.resolveBrackets());
		//return null;
	}
}
