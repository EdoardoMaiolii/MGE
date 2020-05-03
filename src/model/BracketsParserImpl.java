package model;

import java.util.*;

public class BracketsParserImpl implements BracketsParser{//class that resolve brackets -->only one method public
	private RealParser funcRewritten;
	private Pair<Integer,Integer> num_par;
	private String fstring;
	private List<Pair<Integer,Integer>> brackets_place= new ArrayList<>();
	private Pair<Integer,Integer> currentPosPar=null;
	
	public BracketsParserImpl(String str) {
		this.fstring=str;
	}

	
	private void  listAdder() {
		brackets_place.clear();
		num_par=BracketsUtility.countBrackets(fstring);
		if(num_par.getFst()!=0) {//ci sono delle parentesi
			for(int k=0;k<fstring.length();k++) {
				if(fstring.charAt(k)=='(') {
					int fine_par=k+BracketsUtility.endBracket(fstring.substring(k, fstring.length()));
					Pair<Integer,Integer> local = new Pair<Integer,Integer>(k,fine_par);
						brackets_place.add(local);
				}
			}
		}
		
	}
	
	private Pair<Integer,Integer> findMinAndSkim() {// lo scrematore --> toglie le funz dalla lista e restituisce la distanza minore
		Optional<Integer> min_distance=Optional.empty();
		Pair<Integer,Integer> result =null;
		for(int k=0;k<brackets_place.size();k++) {
			Pair<Integer,Integer> current_bracket= brackets_place.get(k);
			if(current_bracket.getFst()!=0 && Character.isLetter(fstring.charAt(current_bracket.getFst()-1))) {//caso in cui non va bene
				brackets_place.remove(k);
				k--;
			}
			else {
				int currentDistance =current_bracket.getSnd()-current_bracket.getFst();
				if(min_distance.isEmpty()) {
					min_distance=Optional.of(currentDistance);
					result=current_bracket;
				}
				else if(currentDistance<min_distance.get()){
					min_distance=Optional.of(currentDistance);
					result=current_bracket;
				}
				
			}
		}
		return result;	
	}
	
	private String getString() {
		listAdder();
		currentPosPar=findMinAndSkim();
		if(currentPosPar!= null)
			return fstring.substring(currentPosPar.getFst()+1,currentPosPar.getSnd());
		else return fstring;
	}
	
	private String attacher(String str) {
		if(currentPosPar!=null)
			return fstring.substring(0,currentPosPar.getFst())+str+fstring.substring(currentPosPar.getSnd()+1);
		else return fstring;
	}
	
	private void checkError() {
		if(!BracketsUtility.checkBrackets(fstring))
			throw new java.lang.IllegalArgumentException();
	}
	
	public String resolveBrackets() {
		checkError();
		funcRewritten = new RealParserImpl(fstring);
		String rewritten;
		String local ;
		listAdder();
		while(brackets_place.size()!=0) {// andiamo avanti finche' ci sono parentesi
			local = getString(); //mi prendo la stringa tra le parentesi piu' corte
			funcRewritten.setString(local);//setto al funcRewriter la giusta stringa
			rewritten=funcRewritten.funcRewriter();//cambio gli operatori della stringa senza parentesi 
			fstring=attacher(rewritten);//ricreo la stringa giusta
		}
		funcRewritten.setString(fstring);//setto al funcRewriter la giusta stringa
		fstring=funcRewritten.funcRewriter();//cambio gli operatori della stringa senza parentesi 
		return fstring;
	}
}
