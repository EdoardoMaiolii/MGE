package model;

public class FunctionParser {// cambiare tipo di ritorno in base alle func di cini
	
	static BracketsParser peeledString;
	static FinalParser func = new FinalParserImpl();
	
	private FunctionParser() {}
	
	public static AlgebricFunction<?> parseString(String str) {
		peeledString = new BracketsParserImpl(str);
		//System.out.println(peeledString.resolvePar()); //decommentare per vedere la stringa riscritta stampata
		return func.resolveFunction(peeledString.resolvePar());
		//return null;
	}
	/*
	public ParserController(String str) {
		this.fstring=str;
	}
	private String resolvePar(String str) {
		String rewritten;
		String local ;
		while(stringa_pelata.getList().size()!=0) {
			local = stringa_pelata.getString(); //mi prendo la stringa tra le parentesi piu' corte
			funcRewritten.setString(local);//setto al funcRewriter la giusta stringa
			rewritten=funcRewritten.funcRewriter();//cambio gli operatori della stringa senza parentesi 
			fstring=stringa_pelata.attacher(rewritten);//ricreo la stringa giusta
			stringa_pelata.setString(fstring);
		}
		funcRewritten.setString(fstring);//setto al funcRewriter la giusta stringa
		fstring=funcRewritten.funcRewriter();//cambio gli operatori della stringa senza parentesi 
		return fstring;
	}
	public Function Controller() {
		stringa_pelata = new ParentesiParserImpl(fstring);
		funcRewritten = new RealParserImpl(fstring);
		stringa_pelata.listAdder();
		System.out.println(resolvePar(fstring));
		return null;
	}*/
	
}
