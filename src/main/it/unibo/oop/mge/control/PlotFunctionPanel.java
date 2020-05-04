package it.unibo.oop.mge.control;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Segment2D;

public final class PlotFunctionPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5244263802622065600L;
    private final Point2D center;
    private final List<Segment2D> segments;

    public PlotFunctionPanel(final int size, final List<Segment2D> segments) {
        this.segments = segments;
        this.center = Point2D.fromDoubles(size / 2, size / 2);
        this.setPreferredSize(new Dimension(size, size));
    }

    @Override
    public void paintComponent(final Graphics g) {
        for (final Segment2D segment : this.segments) {
            g.drawLine((int) (segment.getA().getX() * this.center.getX() + this.center.getX()), (int) (segment.getA().getY() * -this.center.getY() + this.center.getY()),
                    (int) (segment.getB().getX() * this.center.getX() + this.center.getX()), (int) (segment.getB().getY() * -this.center.getY() + this.center.getY()));
        }
    }
}
