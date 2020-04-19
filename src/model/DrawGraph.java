package model;

import java.util.Set;

public interface DrawGraph {

  void generate();

  void load();

  void save();

  Set<String> costants();

  Set<String> digits();

  Set<String> mathFucntions();

  Set<String> punctuation();

  Set<String> settings();
  
  Set<String> variables();
}
