package view;

import java.util.List;

import control.DrawGraphViewObserver;

public interface DrawGraphView {
  
  void start();

  void setObserver(DrawGraphViewObserver observer);
  
  void expressionIncorrect();
  
  void graph(List<Point> points);
  
}
