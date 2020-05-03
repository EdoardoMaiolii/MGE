package it.unibo.oop.mge.control;

public interface DrawGraphViewObserver {
    void newGraph(String function, double min, double max, double rate);
    void load();
    void save();
    void zoomIn();
    void zoomOut();
    void moveUp();
    void moveLeft();
    void moveRight();
    void moveDown();
}
