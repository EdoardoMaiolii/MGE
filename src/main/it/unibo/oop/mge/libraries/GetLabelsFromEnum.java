package it.unibo.oop.mge.libraries;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GetLabelsFromEnum {
    private static final Map<String, String> OPERATORS = Map.of(MathFunction.SUM.getSyntax(), "+",
            MathFunction.SOT.getSyntax(), "-", MathFunction.MUL.getSyntax(), "*", MathFunction.DIV.getSyntax(), "/");

    private GetLabelsFromEnum() {
    };

    public static List<String> getLabelFromConstants() {
        return Constant.getSyntaxList();
    }

    public static List<String> getLabelFromMathFunctions() {
        return MathFunction.getSyntaxList().stream().map(i -> OPERATORS.containsKey(i) ? OPERATORS.get(i) : i)
                .collect(Collectors.toList());
    }

    public static List<String> getLabelFromDigits() {
        return Digits.getListFromEnum();
    }

    public static List<String> getLabelFromPunctuation() {
        return Punctuation.getSyntaxList();
    }

    public static List<String> getLabelFromVariables() {
        return Variable.getSyntaxList();
    }
    public static List<String> getLabelFromProperties() {
        return List.of();
    }
}
