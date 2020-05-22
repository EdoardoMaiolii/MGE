package it.unibo.oop.mge.color;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class VariableColorFactoryTest {
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
        vc = new VariableColorBuilderImpl().setBlue(blue).setGreen(green).setRed(red).build();
        assertTrue(vc.getRed().isPresent());
        assertTrue(vc.getGreen().isPresent());
        assertTrue(vc.getBlue().isPresent());
        assertEquals(vc.getGreen().get().intValue(), green);
        assertEquals(vc.getBlue().get().intValue(), blue);
        assertEquals(vc.getRed().get().intValue(), red);
        /* Set none */
        vc = new VariableColorBuilderImpl().build();
        assertTrue(vc.getRed().isEmpty());
        assertTrue(vc.getGreen().isEmpty());
        assertTrue(vc.getBlue().isEmpty());
    }

}
