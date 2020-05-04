package it.unibo.oop.mge.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Segment2D;

public class PlotFunctionPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5244263802622065600L;
    private final Point2D center;
    private List<Segment2D> segments;

    public PlotFunctionPanel(final int size, final List<Segment2D> segments) {
        super();
        this.segments = segments;
        this.center = Point2D.fromDoubles(size / 2, size / 2);
        this.setPreferredSize(new Dimension(size, size));
    }

    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (final Segment2D segment : this.segments) {
            g.drawLine((int) (segment.getA().getX() * this.center.getX() + this.center.getX()), (int) (segment.getA().getY() * -this.center.getY() + this.center.getY()),
                    (int) (segment.getB().getX() * this.center.getX() + this.center.getX()), (int) (segment.getB().getY() * -this.center.getY() + this.center.getY()));
        }
    }

    public final void updateSegments(final List<Segment2D> newSegments) {
        this.segments = newSegments;
        this.update(getGraphics());
    }
}
