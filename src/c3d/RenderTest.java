package c3d;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public class RenderTest extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Segment2D> output;

	public static void main(String[] args) {

		Point3D a = Point3D.fromDoubles(-100, -100, 0);
		Point3D b = Point3D.fromDoubles(100, -100, 0);
		Point3D c = Point3D.fromDoubles(-100, 100, 0);
		Point3D d = Point3D.fromDoubles(100, 100, 0);

		Segment3D ab = Segment3D.fromPoints(a, b);
		Segment3D bd = Segment3D.fromPoints(b, d);
		Segment3D cd = Segment3D.fromPoints(c, d);
		Segment3D ca = Segment3D.fromPoints(c, a);

		var set = Arrays.asList(ab, bd, cd, ca);

		ModelVisualizer visualizer = new ModelVisualizerImpl();

		visualizer.setModel(set);

		visualizer.setRotationXY(10);
		visualizer.setTranslation(Point3D.fromDoubles(0, 0, 100));
		var set2 = visualizer.render();

		RenderTest dr = new RenderTest(set2);
		JFrame frame = new JFrame("My Drawing");
		frame.add(dr);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		output.forEach(el -> g.drawLine((int) el.getA().getX() + 200, (int) el.getA().getY() + 200,
				(int) el.getB().getX() + 200, (int) el.getB().getY() + 200));
	}

	RenderTest(List<Segment2D> model) {
		this.output = model;
	}

}