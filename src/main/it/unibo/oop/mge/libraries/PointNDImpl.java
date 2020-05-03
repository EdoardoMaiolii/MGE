package it.unibo.oop.mge.libraries;

import java.util.List;

public final class PointNDImpl implements PointND {
    private List<Double> values;
    public PointNDImpl(final List<Double> values) {
        this.values = values;
    }
    @Override
    public List<Double> getCoordinates() {
        return values;
    }
    @Override
    public String toString() {
        return values.toString() + "\n";
    }
}
