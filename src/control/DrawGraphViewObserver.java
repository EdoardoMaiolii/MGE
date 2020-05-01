package control;

public interface DrawGraphViewObserver {

  void newGraph(String function, int max, int min, int rate);

  void load();

  void save();

}
