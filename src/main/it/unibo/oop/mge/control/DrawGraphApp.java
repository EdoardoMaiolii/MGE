package it.unibo.oop.mge.control;

import it.unibo.oop.mge.model.*;
import it.unibo.oop.mge.view.DrawGraphView;
import it.unibo.oop.mge.view.DrawGraphViewImpl;
import it.unibo.oop.mge.c3d.MeshVisualizerImpl;
import it.unibo.oop.mge.libraries.*;;

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
  public void newGraph(String function, double max, double min, double rate) {
      FunctionFeaturesImpl ff = new FunctionFeaturesBuilder()
                      .setFunction(FunctionParser.parse(function))
                      .setIntervals(new Pair<Double,Double>(min,max))
                      .setRate(rate)
                      .build();
      var mesh = new MeshVisualizerImpl();
      mesh.setMesh(ff.getPolygonalModel());
      view.plotGraph(mesh.render());
  }
  
  public static void main(String[] args) {
    new DrawGraphApp();
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
public void zoomIn() {
    // TODO Auto-generated method stub
    
}

@Override
public void zoomOut() {
    // TODO Auto-generated method stub
    
}

@Override
public void moveUp() {
    // TODO Auto-generated method stub
    
}

@Override
public void moveLeft() {
    // TODO Auto-generated method stub
    
}

@Override
public void moveRight() {
    // TODO Auto-generated method stub
    
}

@Override
public void moveDown() {
    // TODO Auto-generated method stub
    
}

}
