package view;

import java.util.List;

import c3d.geometry.Segment2D;
import control.DrawGraphViewObserver;

public interface DrawGraphView {
  
  void start();

  void setObserver(DrawGraphViewObserver observer);
  
  void expressionIncorrect();

  void plotGraph(List<Segment2D> segments);
  
}
