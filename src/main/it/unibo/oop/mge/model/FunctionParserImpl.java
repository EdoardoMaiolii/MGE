package it.unibo.oop.mge.model;

import it.unibo.oop.mge.function.AlgebricFunction;

public class FunctionParserImpl implements FunctionParser {

    private static BracketsParser peeledString;
    private static FinalParser func = new FinalParserImpl();

    private FunctionParserImpl() { }

    protected static AlgebricFunction parseString(final String str) {
        peeledString = new BracketsParserImpl(str);
        //System.out.println(peeledString.resolveBrackets()); // decommentare per vedere la stringa riscritta stampata
        return func.resolveFunction(peeledString.resolveBrackets());
        // return null;
    }
}
