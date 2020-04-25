package view;

import java.util.Set;

import control.DrawGraphViewObserver;

public interface DrawGraphView {
  void start();

  void setObserver(DrawGraphViewObserver observer);
  
  void setSettings(Set<String> settings);
  
  void setVariables(Set<String> variables);
  
  void setMathFunctions(Set<String> mathFunctions);
  
  void setDigits(Set<String> digits);
  
  void setCostants(Set<String> costants);
  
  void setPunctuation(Set<String> punctuation);  
  
  void expressionIncorrect();
}
