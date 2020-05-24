package it.unibo.oop.mge.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionFeaturesTest {
    @Test
    void FunctionFeaturesBuilderTest() {
        assertEquals(new FunctionFeaturesBuilderImpl().build(), null);
        assertEquals(new FunctionFeaturesBuilderImpl().setDecimalPrecision(2).build(), null);
        assertEquals(new FunctionFeaturesBuilderImpl().setDecimalPrecision(2).setFunction(null), null);
    }
}
