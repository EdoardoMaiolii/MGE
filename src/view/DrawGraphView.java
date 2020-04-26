package view;

import control.DrawGraphViewObserver;

public interface DrawGraphView {
  void start();

  void setObserver(DrawGraphViewObserver observer);
  
  void expressionIncorrect();
}
