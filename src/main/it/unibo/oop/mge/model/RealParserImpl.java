package it.unibo.oop.mge.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Pair;

public class RealParserImpl implements RealParser {
    private List<Character> op1 = Arrays.asList('*', '/');
    private List<Character> op2 = Arrays.asList('+', '-');
    private String fstring, currentString;
    private int flength;

    Map<Character, MathFunction> opMap = new HashMap<>();

    public RealParserImpl(final String fstring) {
        this.fstring = fstring;
        flength = this.fstring.length();
        opMap.put('+', MathFunction.SUM);
        opMap.put('*', MathFunction.MUL);
        opMap.put('/', MathFunction.DIV);
        opMap.put('-', MathFunction.SOT);

    }

    private boolean checkStartOfParam(final String currentString, final int dir) { // caso stringa 9 nella somma si deve fermare al 2
        final Pair<Integer, Integer> numBrackets = BracketsUtility.countBrackets(currentString);
        if ((dir == 0 && numBrackets.getFst() == numBrackets.getSnd() + 1)
                || (dir == 1 && numBrackets.getFst() + 1 == numBrackets.getSnd())) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean leftCond(final int k, final int posOp) {
        currentString = fstring.substring(k, posOp);

        if (checkStartOfParam(currentString, 0)) {
            return false;
        } else if (BracketsUtility.checkBrackets(currentString) && opMap.containsKey(fstring.charAt(k))) {
            return false;
        } else if (fstring.charAt(k) == ',' && BracketsUtility.checkBrackets(currentString)) {
            return false;
        } else
            return true;
    }

    private boolean rightCond(int k, int posOp) {
        currentString = fstring.substring(posOp, k);
        if (checkStartOfParam(currentString, 1)) {
            return false;
        } else if (BracketsUtility.checkBrackets(currentString) && opMap.containsKey(fstring.charAt(k))) {
            return false;
        } else if (fstring.charAt(k) == ',' && BracketsUtility.checkBrackets(currentString)) {
            return false;
        } else
            return true;
    }

    private boolean checkMinus(Character ch, int posOp) { // func that add 0 if there is a minus in start of func or
                                                          // before bracket
        if (ch == '-') {
            if (posOp == 0) {
                fstring = '0' + fstring;
                return true;
            } else if (fstring.charAt(posOp - 1) == '(') {
                fstring = fstring.substring(0, posOp) + '0' + fstring.substring(posOp);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void subOp(Character ch, int posOp) { // migliorare il programma con currentString
        if (checkMinus(ch, posOp)) {
            posOp++;
        }
        int k = posOp - 1;
        String fParam, sParam, fStr = "", sStr = "";
        currentString = fstring.substring(k, posOp);
        while (k >= 0 && leftCond(k, posOp)) {
            k--;
        }
        if (k == -1 && fstring.charAt(k + 1) == '(')
            fParam = fstring.substring(k + 2, posOp);
        else
            fParam = fstring.substring(k + 1, posOp);

        if (k >= 0)
            fStr = fstring.substring(0, k + 1); // quello che c'era prima del primo parametro
        k = posOp + 1;

        while (k <= fstring.length() - 1 && rightCond(k, posOp + 1))
            k++;
        if (fstring.charAt(k - 1) == ')')
            sParam = fstring.substring(posOp + 1, k);
        else
            sParam = fstring.substring(posOp + 1, k);

        if (k <= fstring.length())
            sStr = fstring.substring(k, fstring.length()); // quello che c'e' dopo il secondo param
        fstring = fStr + opMap.get(ch).getSyntax() + '(' + fParam + ',' + sParam + ')' + sStr;
    }

    @Override
    public String funcRewriter() {
        int k;
        int numMul = BracketsUtility.countCharacter(fstring, i -> i.equals('*') || i.equals('/'));
        int numSum = BracketsUtility.countCharacter(fstring, i -> i.equals('+') || i.equals('-'));
        while (numSum > 0 || numMul > 0) {
            if (numMul > 0) {
                for (k = 0; !op1.contains(fstring.charAt(k)); k++)
                    ;// va avanti finche' non trova un operatore * o : da sostituire
                subOp(fstring.charAt(k), k);// funz che sostituisce l'operatore , passo l'operatore
                numMul--;
            } else {
                for (k = 0; !op2.contains(fstring.charAt(k)); k++)
                    ;
                subOp(fstring.charAt(k), k);
                numSum--;
            }
        }
        return fstring;
    }

    public void setString(String str) {
        this.fstring = str;
        this.flength = str.length();
    }
}
