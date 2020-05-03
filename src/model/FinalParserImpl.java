package model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dringa.*;

public class FinalParserImpl implements FinalParser{

	private List<String> getParameters(String str){
		int lastVirgola=0;
		List<String> list = new ArrayList<>();
		Pair<Integer,Integer> parTotali;
		for(int k=1;k<str.length();k++) { 
			parTotali=BracketsUtility.countBrackets(str.substring(0,k));
			if(str.charAt(k)==',' && (parTotali.getFst()-parTotali.getSnd())==1) {
				list.add(str.substring(lastVirgola+1,k));
				lastVirgola=k;
			}
		}
		list.add(str.substring(lastVirgola+1,str.length()-1));
		return list;
		
	}
	
	/*
	private void checkAllDigits(String fstring) throws DigitsException {
		int k=0;
		while(Character.isDigit(fstring.charAt(k++)));
		if(fstring.length()!=k+1) 
			throw new DigitsException ();
	}
	private void checkConstants() throws DigitsException, ConstantsException {
		throw new ConstantsException();
	}
	
	private void checkMathFunction(String fstring) throws DigitsException, MathFunctionException {
		if(!MathFunctions.contains(fstring))
			throw new MathFunctionException();
	}
	*/
	private void checkError(String fstring) {
		int k=0;
		while(fstring.length()>k && Character.isDigit(fstring.charAt(k))) {
			k++;
		}
		if(fstring.length()!=k && !MathFunctions.contains(fstring)) 
			throw new java.lang.IllegalArgumentException();
	}
	
	public AlgebricFunction<?> resolveFunction(String fstring)   {//piccola implementazione del controllo dell'errore
		int k=0;
		if(Character.isDigit(fstring.charAt(k))) {
			//checkAllDigits(fstring);
			checkError(fstring);
			return AlgebricFunctionFactory.getValueFunction(Double.valueOf(fstring));
		}
		else {
			while(fstring.charAt(k++)!='(') {//caso parametro singolo
				if(fstring.length()==k) {
					if(Constants.contains(fstring))
						return AlgebricFunctionFactory.getConstantFunction(Constants.valueOf(fstring));
					if(fstring.length()==1)
						return AlgebricFunctionFactory.getParameterFunction(fstring.charAt(0));
					else checkError(fstring);
				}	
			}
			checkError(fstring.substring(0, k-1));
			return AlgebricFunctionFactory.getMathFunction(MathFunctions.valueOf(fstring.substring(0, k-1)), 
					getParameters(fstring.substring(k-1)).stream().map(i->resolveFunction(i)).collect(Collectors.toList()));
		}
	}
}
	
	
	
