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
    this.view.setCostants(this.model.costants());
    this.view.setDigits(this.model.digits());
    this.view.setMathFunctions(this.model.mathFunctions());
    this.view.setPunctuation(this.model.punctuation());
    this.view.setSettings(this.model.settings());
    this.view.setVariables(this.model.variables());
    this.view.start();
  }
  
  @Override
  public void newExpression(String expression) {
    //TODO
  }
  
  public static void main(String[] args) {
    new DrawGraphApp();
  }

}
