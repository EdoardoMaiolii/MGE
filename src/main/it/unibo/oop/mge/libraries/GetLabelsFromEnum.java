package it.unibo.oop.mge.libraries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GetLabelsFromEnum {

    static List<String> getLabelFromConstants() {
        return Constant.getListFromEnum();
    }

    static List<String> getLabelFromMathFunctions() {
        final Map<String, String> operators = new HashMap<>();
        operators.put(MathFunction.SUM.getSyntax(), "+");
        operators.put(MathFunction.SOT.getSyntax(), "-");
        operators.put(MathFunction.MUL.getSyntax(), "*");
        operators.put(MathFunction.DIV.getSyntax(), "/");
        List<String> list = MathFunction.getListFromEnum();
        operators.forEach((a, b) -> list.set(list.indexOf(a), b));
        return list;
    }

    static List<String> getLabelFromDigits() {
        return Digits.getListFromEnum();
    }

    static List<String> getLabelFromPunctuation() {
        return Punctuation.getListFromEnum();
    }

    static List<String> getLabelFromVariables() {
        return Variables.getListFromEnum();
    }
}
