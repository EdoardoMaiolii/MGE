package it.unibo.oop.mge.c3d;

import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Segment2D;

public interface Mesh2D {

    List<Segment2D> getSegments();

    static Mesh2D of(List<Segment2D> segments) {
        return new Mesh2DImpl(segments);
    }
}
