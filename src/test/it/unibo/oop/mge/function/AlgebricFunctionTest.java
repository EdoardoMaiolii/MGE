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
        /* Create a constant function with value 'e' */
        var e = AlgebricFunctionFactory.getConstantFunction(Constant.E);
        assertTrue(e.getParameters().isEmpty());
        assertEquals(e.resolve(values), Constant.E.resolve());
        /* Create a variable function with name 'x' */
        var x = AlgebricFunctionFactory.getParameterFunction(Variable.X);
        assertEquals(x.resolve(values), Double.valueOf(10.0));
        assertTrue(x.getParameters().isEmpty());
        var value2 = AlgebricFunctionFactory.getValueFunction(2.0);
        /* Create a value function with value '2' */
        assertEquals(value2.resolve(values), Double.valueOf(2.0));
        assertTrue(value2.getParameters().isEmpty());
        /*
         * Create a mathematical function 'sum' passing the constant function 'e' and
         * the variable function 'x'
         */
        var sum = AlgebricFunctionFactory.getMathFunction(MathFunction.SUM, List.of(e, x));
        assertFalse(sum.getParameters().isEmpty());
        assertEquals(sum.resolve(values), Double.valueOf(Constant.E.resolve() + values.get(Variable.X)));
    }
}
