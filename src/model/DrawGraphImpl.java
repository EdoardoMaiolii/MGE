package model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import libraries.Constants;

public class DrawGraphImpl implements DrawGraph {
  
  public DrawGraphImpl() {
  }

  @Override
  public void generate() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void load() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void save() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Set<String> costants() {
    return Arrays.asList(Constants.values()).stream().map(i -> i.getLabel()).collect(Collectors.toList())
  }

  @Override
  public Set<String> digits() {
    return Arrays.asList(Digit.values()).stream().map(i -> i.getLabel()).collect(Collectors.toList());
  }

  @Override
  public Set<String> mathFucntions() {
	  return Arrays.asList(MathFunctions.values()).stream().map(i -> i.getLabel()).collect(Collectors.toList());
  }

  @Override
  public Set<String> punctuation() {
	 return Arrays.asList(Punctuations.values()).stream().map(i -> i.getLabel()).collect(Collectors.toList());
  }

  @Override
  public Set<String> settings() {
	  return Arrays.asList(Settings.values()).stream().map(i -> i.getLabel()).collect(Collectors.toList());
  }
  
  @Override
  public Set<String> variables() {
	  return Arrays.asList(Variables.values()).stream().map(i -> i.getLabel()).collect(Collectors.toList());
  }
  
}
