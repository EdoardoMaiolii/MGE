package it.unibo.oop.mge.c3d;

import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Segment2D;

public class Mesh2DImpl implements Mesh2D {
    private final List<Segment2D> segments;

    // package protected
    Mesh2DImpl(final List<Segment2D> segments) {
        super();
        this.segments = segments;
    }

    @Override
    public final List<Segment2D> getSegments() {
        return segments;
    }
}
