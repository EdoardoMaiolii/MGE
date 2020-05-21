package it.unibo.oop.mge.libraries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GetLabelsFromEnum {
    private GetLabelsFromEnum() {
    };

    public static List<String> getLabelFromConstants() {
        return Constant.getListFromEnum();
    }

    public static List<String> getLabelFromMathFunctions() {
        final Map<String, String> operators = new HashMap<>();
        operators.put(MathFunction.SUM.getSyntax(), "+");
        operators.put(MathFunction.SOT.getSyntax(), "-");
        operators.put(MathFunction.MUL.getSyntax(), "*");
        operators.put(MathFunction.DIV.getSyntax(), "/");
        List<String> list = MathFunction.getListFromEnum();
        operators.forEach((a, b) -> list.set(list.indexOf(a), b));
        return list;
    }

    public static List<String> getLabelFromDigits() {
        return Digits.getListFromEnum();
    }

    public static List<String> getLabelFromPunctuation() {
        return Punctuation.getListFromEnum();
    }

    public static List<String> getLabelFromVariables() {
        return Variables.getListFromEnum();
    }
}
