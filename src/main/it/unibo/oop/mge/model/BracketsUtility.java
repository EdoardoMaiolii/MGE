package it.unibo.oop.mge.model;

import java.util.function.Function;

import it.unibo.oop.mge.libraries.Pair;

public final class BracketsUtility {

    private BracketsUtility() { }

    /*
     public static int countCharacter(final String str, final Character c) {
         return (int) str.chars().mapToObj(i->(char) i).filter(i->i.equals(c)).count(); 
     }*/
     
    
    public static int countCharacter(String fstring, Function<Character, Boolean> function) {
        return (int) fstring.chars().mapToObj(i -> (char) i).filter(i -> function.apply(i)).count();
    }
    
    public static Pair<Integer, Integer> countBrackets(final String str) { // count open and close brackets
        return new Pair<Integer, Integer>(countCharacter(str, i -> i.equals('(')),
                countCharacter(str, i -> i.equals(')')));
    }

    public static boolean checkBrackets(final String currentString) { // check if open brackets are equals to close
                                                                      // brackets
        final Pair<Integer, Integer> numBrackets = countBrackets(currentString);
        if (numBrackets.getFst() - numBrackets.getSnd() == 0)
            return true;
        else
            return false;
    }

    public static int endBracket(final String str, int offset) { // return the close Bracket of the first openBracket
                                                                 // instance
        Pair<Integer, Integer> p;
        for (int k = 1; k < str.length(); k++) {
            p = countBrackets(str.substring(0, k + 1));
            if (p.getFst() == p.getSnd())
                return k + offset;
        }
        return 0;
    }

}
