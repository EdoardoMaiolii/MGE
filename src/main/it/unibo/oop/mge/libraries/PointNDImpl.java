package it.unibo.oop.mge.libraries;

import java.util.List;

public class PointNDImpl implements PointND {
    private List<Double> values;

    public PointNDImpl(final List<Double> values) {
        this.values = values;
    }

    @Override
    public final List<Double> getValues() {
        return this.values;
    }
}
