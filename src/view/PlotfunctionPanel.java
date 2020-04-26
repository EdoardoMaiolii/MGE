package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import model.PointNDimention;

public class PlotfunctionPanel extends JPanel{
  private final int size;
  private final double maxValue;
  private final double minValue;
  private final Point center;
  private final List<Point> points;
  
  public PlotFunctionPanel(int size, double rangeValue, List<Point> points) {
    this.size = size;
    this.maxValue = rangeValue;
    this.minValue = -rangeValue;
    this.center = new Point(size/2, size/2);
    this.points = points;
    //da cambiare
    this.setPreferredSize(new Dimension(size, size));
  }
  
  public void paintComponent(Graphics g) {
    //asse delle y
    g.drawLine(size/2, 0, size/2, size);
    //asse delle x
    g.drawLine(0, size/2, size, size/2);
    for(int i=0; i<points.size(); i++) {
      g.drawString(".", ((int)(this.points.get(i).getCoordinates().get(0)*50+this.center.getX())), 
          (int)(this.points.get(i).getCoordinates().get(1)*50+this.center.getY()));
//      g.drawLine((int)(this.points.get(i).getCoordinates().get(0)*50+this.center.getX()), 
//          (int)(this.points.get(i).getCoordinates().get(1)*50+this.center.getY()),
//          (int)(this.points.get(i+1).getCoordinates().get(0)*50+this.center.getX()),
//          (int)(this.points.get(i+1).getCoordinates().get(1)*50+this.center.getY()));
    }
  }
}
