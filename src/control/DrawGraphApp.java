package control;

import model.DrawGraph;
import model.DrawGraphImpl;
import view.DrawGraphView;
import view.DrawGraphViewImpl;

public class DrawGraphApp implements DrawGraphViewObserver{

  private final DrawGraph model;
  private final DrawGraphView view;
  
  public DrawGraphApp() {
    this.model = new DrawGraphImpl();
    this.view = new DrawGraphViewImpl();
    this.view.setObserver(this);
    this.view.start();
  }
  
  @Override
  public void newExpression(String expression) {
    try {
      this.model.parse(expression);
      this.view.graph(this.model.generateGraph());
    } catch (IllegalArgumentException e) {
      this.view.expressionIncorrect();
    }
  }
  
  public static void main(String[] args) {
    new DrawGraphApp();
  }

}
