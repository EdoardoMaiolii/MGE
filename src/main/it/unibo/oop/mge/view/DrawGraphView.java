package it.unibo.oop.mge.view;

import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.control.DrawGraphViewObserver;

public interface DrawGraphView {
    void start();

    void setObserver(DrawGraphViewObserver observer);

    void plotGraph(List<Segment2D> segments);

    void updateGraph(List<Segment2D> newSegments);

    void expressionIncorrect();

    void IOError();
}
