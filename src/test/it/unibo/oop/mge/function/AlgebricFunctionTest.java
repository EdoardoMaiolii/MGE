package it.unibo.oop.mge.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.libraries.Constant;
import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Variable;

public class AlgebricFunctionTest {
    @Test
    void FactoryTest() {
        Map<Variable, Double> values = Map.of(Variable.X, 10.0, Variable.Y, -2.0);
        var a = AlgebricFunctionFactory.getConstantFunction(Constant.E);
        assertTrue(a.getParameters().isEmpty());
        assertEquals(a.resolve(values), Constant.E.resolve());
        var b = AlgebricFunctionFactory.getParameterFunction(Variable.X);
        assertEquals(b.resolve(values), Double.valueOf(10.0));
        assertTrue(b.getParameters().isEmpty());
        var c = AlgebricFunctionFactory.getValueFunction(2.0);
        assertEquals(c.resolve(values), Double.valueOf(2.0));
        assertTrue(c.getParameters().isEmpty());
        var d = AlgebricFunctionFactory.getMathFunction(MathFunction.SUM, List.of(a, b));
        assertFalse(d.getParameters().isEmpty());
        assertEquals(d.resolve(values), Double.valueOf(Constant.E.resolve() + values.get(Variable.X)));
    }
}
