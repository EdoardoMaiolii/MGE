package model;
import dringa.Pair;

public class BracketsUtility {

	private BracketsUtility() {}
	
	private static int countCharacter(String str,Character c) {
		return (int) str.chars().mapToObj(i->(char) i).filter(i->i.equals(c)).count();
	}
	
	public static Pair<Integer,Integer> countBrackets(String str){ //count open and close brackets
		return new Pair<Integer,Integer>(countCharacter(str,'('),countCharacter(str,')'));
	}
	
	public static boolean checkBrackets(String currentString) {//check if open brackets are equals to close brackets
		final Pair<Integer,Integer> numBrackets = countBrackets(currentString);
		if(numBrackets.getFst()-numBrackets.getSnd()==0) 
			return true;
		else return false;
	} 
	
	public static int endBracket(String str) { //return the close Bracket of the first openBracket instance
		Pair<Integer,Integer> p;
		for(int k=1;k<str.length();k++) {
			p=countBrackets(str.substring(0, k+1));
			if(p.getFst()==p.getSnd())
				return k;
		}
		return 0;
	}
	
}
