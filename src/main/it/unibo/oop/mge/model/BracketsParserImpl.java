package it.unibo.oop.mge.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.oop.mge.libraries.Pair;

public class BracketsParserImpl implements BracketsParser { //class that resolve brackets -->only one method public
    private RealParser funcRewritten;
    private Pair<Integer, Integer> numBrackets;
    private String fstring;
    private List<Pair<Integer, Integer>> bracketsPlace = new ArrayList<>();
    private Pair<Integer, Integer> currentPosPar = null;

    public BracketsParserImpl(final String str) {
        this.fstring = str;
    }

    private void  listAdder() {
        bracketsPlace.clear();
        numBrackets = BracketsUtility.countBrackets(fstring);
        if (numBrackets.getFst() != 0) { //ci sono delle parentesi
            /*
            bracketsPlace.addAll(IntStream.range(0, fstring.length())
                    .filter(i -> fstring.charAt(i) == '(')
                    .mapToObj(i -> new Pair<Integer,Integer>(i,
                                  BracketsUtility.endBracket(fstring.substring(i))))
                    .collect(Collectors.toList()));
            */
            IntStream.range(0, fstring.length())
                       .filter(i -> fstring.charAt(i) == '(')
                       .forEach(i -> bracketsPlace.add(new Pair<Integer,Integer>(i,
                                     BracketsUtility.endBracket(fstring.substring(i),i))));
                                     
           /*
            List<Integer> ciao = IntStream.range(0, fstring.length())
                    .filter(i -> fstring.charAt(i) == '(').mapToObj(i -> (int) i).collect(Collectors.toList());
            List<Integer> p = ciao;
            */
    	}
    }

    private Pair<Integer,Integer> findMinAndSkim() {// lo scrematore --> toglie le funz dalla lista e restituisce la distanza minore
    	Optional<Integer> minDistance=Optional.empty();
    	Pair<Integer,Integer> result =null;
    	for (int k = 0; k < bracketsPlace.size(); k++) {
    		Pair<Integer,Integer> currentBracket= bracketsPlace.get(k);
    		if (currentBracket.getFst() != 0 && Character.isLetter(fstring.charAt(currentBracket.getFst() - 1))) {//caso in cui non va bene
    			bracketsPlace.remove(k);
    			k--;
    		}
    		else {
    			int currentDistance =currentBracket.getSnd()-currentBracket.getFst();
    			if(minDistance.isEmpty()) {
    				minDistance=Optional.of(currentDistance);
    				result=currentBracket;
    			}
    			else if(currentDistance<minDistance.get()){
    				minDistance=Optional.of(currentDistance);
    				result=currentBracket;
    			}
    		}
    	}
    	return result;	
    }

    private String getString() {
    	listAdder();
    	currentPosPar=findMinAndSkim();
    	if (currentPosPar != null) {
    		return fstring.substring(currentPosPar.getFst() + 1, currentPosPar.getSnd());
    	}
    	else {
    	    return fstring;
    	}
    }

    private String attacher(final String str) {
    	if (currentPosPar != null) {
    	    return fstring.substring(0,currentPosPar.getFst())+str+fstring.substring(currentPosPar.getSnd()+1);
    	}
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
    	String local;
    	listAdder();
    	while(bracketsPlace.size()!=0) {// andiamo avanti finche' ci sono parentesi
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
