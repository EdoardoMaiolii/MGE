package model;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class RealParserImpl implements RealParser{
	boolean status=false;
	private List<Character> op1 = Arrays.asList( '*', '/');
	private List<Character> op2 = Arrays.asList('+', '-');
	private String fstring,currentString;
	private int flength;
	Map<Integer,Character> posOp= new HashMap<>();
	
	Map<Character,MathFunctions> opMap= new HashMap<>();
	
	public RealParserImpl(String fstring) {
		this.fstring=fstring;
		flength=this.fstring.length();
		opMap.put('+', MathFunctions.SUM);
		opMap.put('*', MathFunctions.MUL);
		opMap.put('/', MathFunctions.DIV);
		opMap.put('-', MathFunctions.SOT);
		
	}
	
	private int numMulDiv() {
		int tmp=0;
		for(int k=0;k<flength;k++) {
			if(fstring.charAt(k)=='*') {
				posOp.put(k,'*');
				tmp++;
			}
			else if(fstring.charAt(k)=='/') {
				posOp.put(k,'/');
				tmp++;
			}
		}
		return tmp;
	}
	
	private int numSumSot() {
		int tmp=0;
		for(int k=0;k<flength;k++) {
			if(fstring.charAt(k)=='+') {
				posOp.put(k,'+');
				tmp++;
			}
			else if(fstring.charAt(k)=='-') {
				posOp.put(k,'-');
				tmp++;
			}
		}
		return tmp;
	}
	private String newStr(String oT,String fP,String sP) {
		return oT+'('+fP+','+sP+')';
	}
	
	
	private boolean check_brutale(String currentString) { //caso stringa 9 nella somma si deve fermare al 2
		final Pair<Integer,Integer> numBrackets =BracketsUtility.countBrackets(currentString);
		if(numBrackets.getFst()==numBrackets.getSnd()+1)
			return true;
		else return false;
	} 
	
	private boolean leftCond(int k,int posOp) {
		currentString=fstring.substring(k, posOp);

		if(check_brutale(currentString)) {
			return false;
		}
		else if(BracketsUtility.checkBrackets(currentString) && opMap.containsKey(fstring.charAt(k))) {
			return false;
		}
		else return true;
	}
	
	private boolean rightCond(int k,int posOp) {
		currentString=fstring.substring(posOp, k);

		if(status && (fstring.charAt(k)=='(' || fstring.charAt(k)==')')) {
			return false;
		}
		else if(BracketsUtility.checkBrackets(currentString) && opMap.containsKey(fstring.charAt(k))) {
			return false;
		}
		else if(opMap.containsKey(fstring.charAt(k))) {
			return false;
		}
		else return true;
	}

	private void subOpMul(Character ch,int posOp) { //migliorare il programma con currentString
		int k=posOp-1;
		String opType=opMap.get(ch).getSyntax();
		String fParam,sParam,fStr = "",sStr = "";
		currentString=fstring.substring(k, posOp);
		while(k>=0 && leftCond(k,posOp)) {
			k--;
		}
		if(k==-1 && fstring.charAt(k+1)=='(') fParam=fstring.substring(k+2,posOp);
		else fParam=fstring.substring(k+1, posOp);
		status=false;
		
 		if(k>=0) fStr=fstring.substring(0, k+1); // quello che c'era prima del primo parametro
		k=posOp+1;
		

		while(k<=fstring.length()-1 && rightCond(k,posOp+1)) 
			k++;
		if(fstring.charAt(k-1)==')') sParam=fstring.substring(posOp+1,k);
		else sParam=fstring.substring(posOp+1, k);
		status=false;
		
		if(k<=fstring.length()) sStr=fstring.substring(k, fstring.length()); //quello che c'e' dopo il secondo param
		fstring=fStr+newStr(opType,fParam,sParam);
		fstring=fstring.concat(sStr);
	}
	
	@Override
	public String funcRewriter() {
		int k;
		int numMul=numMulDiv(); //trova operatori * e :
		int numSum=numSumSot();//trova operatori + e -
		while(numSum>0 || numMul>0) {
			if(numMul>0) {
				for(k=0;!op1.contains(fstring.charAt(k));k++);//va avanti finche' non trova un operatore * o : da sostituire
				subOpMul(fstring.charAt(k),k);//funz che sostituisce l'operatore , passo l'operatore
				numMul--;
			}
			else {
				for(k=0;!op2.contains(fstring.charAt(k));k++);
				subOpMul(fstring.charAt(k),k);
				numSum--;
			}
		}
 		return fstring;
	}
	
	public void setString(String str) {
		this.fstring=str;
		this.flength=str.length();
	}

}
