package it.unibo.oop.mge.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.function.AlgebricFunction.Types;
import it.unibo.oop.mge.libraries.Constant;
import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Variable;

public class AlgebricFunctionTest {
    @Test
    void FactoryTest() {
        AlgebricFunction af = AlgebricFunctionFactory.getConstantFunction(Constant.E);
        assertEquals(af.getType(), Types.CONSTANT);
        assertTrue(af.isConstant());
        assertFalse(af.isVariable());
        assertFalse(af.isMathFunction());
        assertTrue(af.getParameters().isEmpty());
        af = AlgebricFunctionFactory.getParameterFunction(Variable.X);
        assertFalse(af.isConstant());
        assertTrue(af.isVariable());
        assertFalse(af.isMathFunction());
        assertTrue(af.getParameters().isEmpty());
        af = AlgebricFunctionFactory.getValueFunction(2.0);
        assertTrue(af.isConstant());
        assertFalse(af.isVariable());
        assertFalse(af.isMathFunction());
        assertTrue(af.getParameters().isEmpty());
        af = AlgebricFunctionFactory.getMathFunction(MathFunction.LN, List.of());
        assertFalse(af.isConstant());
        assertFalse(af.isVariable());
        assertTrue(af.isMathFunction());
        assertFalse(af.getParameters().isEmpty());
    }
}
