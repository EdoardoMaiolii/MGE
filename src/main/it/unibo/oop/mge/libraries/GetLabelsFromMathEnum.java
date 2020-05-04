package it.unibo.oop.mge.libraries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GetLabelsFromMathEnum {
    private GetLabelsFromMathEnum() { };
    public static List<String> getLabelFromConstants() {
        return Constants.getListFromEnum();
    }
    public static List<String> getLabelFromMathFunctions() {
        final Map<String, String> operators = new HashMap<>();
        operators.put(MathFunctions.SUM.getSyntax(), "+");
        operators.put(MathFunctions.SOT.getSyntax(), "-");
        operators.put(MathFunctions.MUL.getSyntax(), "*");
        operators.put(MathFunctions.DIV.getSyntax(), "/");
        List<String> list = MathFunctions.getListFromEnum();
        operators.forEach((a, b) -> list.set(list.indexOf(a), b));
        return list;
    }
}
