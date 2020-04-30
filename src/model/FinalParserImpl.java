package model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;





public class FinalParserImpl implements FinalParser{

	
	private List<String> getParameters(String str){
		int lastVirgola=0;
		List<String> list = new ArrayList<>();
		Pair<Integer,Integer> parTotali;
		for(int k=1;k<str.length();k++) { 
			parTotali=countParentesi(str.substring(0,k));
			if(str.charAt(k)==',' && (parTotali.getFst()-parTotali.getSnd())==1) {
				list.add(str.substring(lastVirgola+1,k));
				lastVirgola=k;
			}
		}
		list.add(str.substring(lastVirgola+1,str.length()-1));
		return list;
		
	}
	private Pair<Integer,Integer> countParentesi(String str){
		int parAperte=0,parChiuse=0;
		for(int k=0;k<str.length();k++) {
			if(str.charAt(k)=='(')
				parAperte++;
			else if(str.charAt(k)==')')
				parChiuse++;
		}
		return new Pair<Integer,Integer>(parAperte,parChiuse);
	}
	
	
	public AlgebricFunction<?> resolveFunction(String fstring) {
		int k=0;
		if(Character.isDigit(fstring.charAt(k)))
			return AlgebricFunctionFactory.getValueFunction(Double.valueOf(fstring));
		else {
			while(fstring.charAt(k++)!='(') {//caso parametro singolo
				if(fstring.length()==k) {
					return AlgebricFunctionFactory.getParameterFunction(fstring.charAt(0));
				}	
			}
			return AlgebricFunctionFactory.getMathFunction(MathFunctions.valueOf(fstring.substring(0, k-1)), 
					getParameters(fstring.substring(k-1)).stream().map(i->resolveFunction(i)).collect(Collectors.toList()));
		}
	}
}
	
	
	
