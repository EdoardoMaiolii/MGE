package it.unibo.oop.mge.functionfeatures;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.color.VariableColorBuilderImpl;
import it.unibo.oop.mge.function.AlgebricFunctionFactory;
import it.unibo.oop.mge.model.FunctionFeaturesBuilderImpl;

public class FunctionFeaturesBuilderTest {
    @Test
    void illegalArgumentTest() {
        /* We check all IllegalArgumentException that must be handle */
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setDecimalPrecision(-2));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setDinamicColor(null));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setDinamicColor(null));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setFunction(null));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setIntervals(10, -10));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setRate(0));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setRate(-2));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().setStaticColor(null));
    }

    @Test
    void illegalStateTest() {
        /* We check all IllegalStateException that must be handle */
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().setDecimalPrecision(2).build());
        assertThrows(IllegalStateException.class, () -> new FunctionFeaturesBuilderImpl()
                .setFunction(AlgebricFunctionFactory.getValueFunction(2.0)).build());
        assertThrows(IllegalStateException.class, () -> new FunctionFeaturesBuilderImpl()
                .setFunction(AlgebricFunctionFactory.getValueFunction(2.0)).setIntervals(-1, 1).build());
        assertThrows(IllegalStateException.class, () -> new FunctionFeaturesBuilderImpl()
                .setFunction(AlgebricFunctionFactory.getValueFunction(2.0)).setIntervals(-1, 1).setRate(0.1).build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().setFunction(AlgebricFunctionFactory.getValueFunction(2.0))
                        .setIntervals(-1, 1).setRate(0.1)
                        .setDinamicColor(new VariableColorBuilderImpl().setBlue(10).build())
                        .setDinamicColor(new VariableColorBuilderImpl().setBlue(10).build()).build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().setFunction(AlgebricFunctionFactory.getValueFunction(2.0))
                        .setIntervals(-1, 1).setRate(0.1)
                        .setDinamicColor(new VariableColorBuilderImpl().setBlue(10).build()).setStaticColor(Color.black)
                        .build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().setFunction(AlgebricFunctionFactory.getValueFunction(2.0))
                        .setIntervals(-1, 1).setRate(0.1).setStaticColor(Color.black).setStaticColor(Color.black)
                        .build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().setFunction(AlgebricFunctionFactory.getValueFunction(2.0))
                        .setIntervals(-1, 1).setRate(0.1)
                        .setDinamicColor(new VariableColorBuilderImpl().setBlue(10).build()).setStaticColor(Color.black)
                        .build());
    }
}
