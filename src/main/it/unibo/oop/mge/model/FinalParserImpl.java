package it.unibo.oop.mge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.function.AlgebricFunctionFactory;
import it.unibo.oop.mge.libraries.Constant;
import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.libraries.Variable;

public class FinalParserImpl implements FinalParser {
    private static boolean isfloat = false;
    
    private List<String> getParameters(String str) {
        int lastVirgola = 0;
        List<String> list = new ArrayList<>();
        Pair<Integer, Integer> parTotali;
        for (int k = 1; k < str.length(); k++) {
            parTotali = BracketsUtility.countBrackets(str.substring(0, k));
            if (str.charAt(k) == ',' && (parTotali.getFst() - parTotali.getSnd()) == 1) {
                list.add(str.substring(lastVirgola + 1, k));
                lastVirgola = k;
            }
        }
        list.add(str.substring(lastVirgola + 1, str.length() - 1));
        return list;
    }
    
    /*
    private List<Integer> splitWithCond(String str) {
        List<Integer> a = new ArrayList<>();
        IntStream.range(0,str.length())
                         .filter(i-> str.charAt(i) == ',' && BracketsUtility.checkBrackets(str.substring(1, i)))
                         .forEach(i-> a.add(i));
                         
        return a;
                         
    }*/
    
    /*
     * private void checkError(String fstring) { int k = 0; while (fstring.length()
     * > k && (Character.isDigit(fstring.charAt(k)) ||
     * BracketsUtility.countCharacter(fstring, i -> i.equals('.')) == 1)) { k++; }
     * if (fstring.length() != k &&
     * !MathFunction.getListFromEnum().contains(fstring)) throw new
     * java.lang.IllegalArgumentException(); }
     */
    /*
    private void checkError(String fstring) {
        if (BracketsUtility.countCharacter(fstring, i -> i.equals('.'))
                + BracketsUtility.countCharacter(fstring, i -> Character.isDigit(i)) == fstring.length()
                && BracketsUtility.countCharacter(fstring, i -> i.equals('.')) <= 1) {
        } else if (MathFunction.getListFromEnum().contains(fstring)) {
        } else {
            throw new java.lang.IllegalArgumentException();
        }
    }
    */
    /*
     * public AlgebricFunction resolveFunction(String fstring) {
     *  int k = 0; 
     *  if (Character.isDigit(fstring.charAt(k))) { 
         *  checkError(fstring); 
         *  return AlgebricFunctionFactory.getValueFunction(Double.valueOf(fstring)); 
     } else {
         * while (fstring.charAt(k++) != '(') {
         *  if (fstring.length() == k) { 
         *          Optional<Constant> opCos = Constant.getConstantFromSyntax(fstring);
         *          if (opCos.isPresent()) 
         *                  return AlgebricFunctionFactory.getConstantFunction(opCos.get()); 
         *          else if
         *                  (fstring.length() == 1)
         *                   return AlgebricFunctionFactory.getParameterFunction(fstring.charAt(0)); 
         *          else
         *               checkError(fstring); 
         *                  } 
         *          } 
         * checkError(fstring.substring(0, k - 1));
         *  return AlgebricFunctionFactory.getMathFunction(
         * MathFunction.getMathFunctionFromSyntax(fstring.substring(0, k - 1)).get(),
         * getParameters(fstring.substring(k - 1)).stream().map(i -> resolveFunction(i))
         * .collect(Collectors.toList())); 
     *    } 
     * }
     */
    // per essere un numero il numero dei punti piu' il numero di numeri deve essere
    // uguale alla lungehzza
    // della stringa in piu il numero dei punti deve essere al massimo 1
    private boolean checkDigit(String fstring) {
        return BracketsUtility.countCharacter(fstring, i -> i.equals('.'))
                + BracketsUtility.countCharacter(fstring, i -> Character.isDigit(i)) == fstring.length()
                && BracketsUtility.countCharacter(fstring, i -> i.equals('.')) <= 1;
    }

    private void throwEx() {
        throw new java.lang.IllegalArgumentException();
    }

    public AlgebricFunction resolveFunction(final String fstring) {// piccola implementazione del controllo dell'errore
        if (checkDigit(fstring)) {
            return AlgebricFunctionFactory.getValueFunction(Double.valueOf(fstring));
        } else {// caso in cui o e' un nome di funzione, costante o parametro
            // while (fstring.charAt(k++) != '(') {
            if (BracketsUtility.countCharacter(fstring, i -> i.equals('(')) == 0)// se non ci sono parentesi
                if (fstring.equals("x") || fstring.equals("y")) {
                    return AlgebricFunctionFactory.getParameterFunction(Variable.getVariableFromSyntax(fstring.charAt(0)));
                } else {
                    return AlgebricFunctionFactory.getConstantFunction(Constant.getConstantFromSyntax(fstring));
                }
        }
        if (MathFunction.getSyntaxList().contains(fstring.substring(0, fstring.indexOf("(")))) {
            return AlgebricFunctionFactory.getMathFunction(
                    MathFunction.getMathFunctionFromSyntax(fstring.substring(0, fstring.indexOf("("))),
                    getParameters(fstring.substring(fstring.indexOf("("))).stream().map(i -> resolveFunction(i))
                            .collect(Collectors.toList()));
        } else
            throwEx();
        return null;
    }
}
