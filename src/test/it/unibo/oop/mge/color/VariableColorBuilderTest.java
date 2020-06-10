package it.unibo.oop.mge.color;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class VariableColorBuilderTest {
    @Test
    void SetColorsTest() {
        final int red = 10;
        final int green = 150;
        final int blue = 0;
        /* Set red and green */
        VariableColor vc = new VariableColorBuilderImpl().setRed(red).setGreen(green).build();
        assertTrue(vc.getRed().isPresent());
        assertTrue(vc.getGreen().isPresent());
        assertTrue(vc.getBlue().isEmpty());
        assertTrue(vc.getRed().get().equals(red));
        assertTrue(vc.getGreen().get().equals(green));
        /* Set red and blue */
        vc = new VariableColorBuilderImpl().setRed(red).setBlue(blue).build();
        assertTrue(vc.getRed().isPresent());
        assertTrue(vc.getGreen().isEmpty());
        assertTrue(vc.getBlue().isPresent());
        assertEquals(vc.getRed().get().intValue(), red);
        assertEquals(vc.getBlue().get().intValue(), blue);
        /* Set blue and green */
        vc = new VariableColorBuilderImpl().setBlue(blue).setGreen(green).build();
        assertTrue(vc.getRed().isEmpty());
        assertTrue(vc.getGreen().isPresent());
        assertTrue(vc.getBlue().isPresent());
        assertEquals(vc.getGreen().get().intValue(), green);
        assertEquals(vc.getBlue().get().intValue(), blue);
        /* Set all */
        assertThrows(IllegalStateException.class,
                () -> new VariableColorBuilderImpl().setBlue(blue).setGreen(green).setRed(red).build());
        /* Set none */
        vc = new VariableColorBuilderImpl().build();
        assertTrue(vc.getRed().isEmpty());
        assertTrue(vc.getGreen().isEmpty());
        assertTrue(vc.getBlue().isEmpty());
    }

    void SetMultipleTimes() {
        final int red = 10;
        final int green = 150;
        final int blue = 0;
        /* Set blue 2 times */
        assertThrows(IllegalStateException.class, () -> new VariableColorBuilderImpl().setBlue(blue).setBlue(blue));
        /* Set red 2 times */
        assertThrows(IllegalStateException.class, () -> new VariableColorBuilderImpl().setRed(red).setRed(red));
        /* Set green 2 times */
        assertThrows(IllegalStateException.class, () -> new VariableColorBuilderImpl().setGreen(green).setGreen(green));
    }

    void BuildMultipleTimes() {
        /* Build 2 times */
        var builder = new VariableColorBuilderImpl();
        builder.build();
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    void trySetBadValues() {
        final int red = -5;
        final int green = 1050;
        final int blue = -1000;
        /* Set blue */
        assertThrows(IllegalArgumentException.class, () -> new VariableColorBuilderImpl().setBlue(blue).setBlue(blue));
        /* Set red */
        assertThrows(IllegalArgumentException.class, () -> new VariableColorBuilderImpl().setRed(red).setRed(red));
        /* Set green */
        assertThrows(IllegalArgumentException.class,
                () -> new VariableColorBuilderImpl().setGreen(green).setGreen(green));
    }
}
