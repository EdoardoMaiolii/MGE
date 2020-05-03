package c3d;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import c3d.geometry.Point2D;
import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public class RenderTest extends Canvas {

	/**
	 *
	 */
	private static final int screenSize = 200;
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

		Segment3D ab = Segment3D.fromPoints(a, b, Color.RED);
		Segment3D bd = Segment3D.fromPoints(b, d);
		Segment3D cd = Segment3D.fromPoints(c, d);
		Segment3D ca = Segment3D.fromPoints(c, a);

		var set = Arrays.asList(ab, bd, cd, ca);

		MeshVisualizer visualizer = MeshVisualizer.create();

		visualizer.setMesh(set);

		visualizer.setTranslation(Point3D.fromDoubles(0, 0, 20));
		var output = visualizer.render();

		RenderTest dr = new RenderTest(output);
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
		output.stream()
				.map(seg -> Segment2D.fromPoints(Point2D.fromDoubles(seg.getA().getX(), -seg.getA().getY()),
						Point2D.fromDoubles(seg.getB().getX(), -seg.getB().getY()), seg.getColor()))
				.map(el -> el.transformed(coord -> coord * screenSize + screenSize)).forEach(el -> {
					System.out.println(el.getColor());
					g.setColor(el.getColor());
					g.drawLine((int) el.getA().getX(), (int) el.getA().getY(), (int) el.getB().getX(),
							(int) el.getB().getY());
				});
	}

	RenderTest(List<Segment2D> model) {
		this.output = model;
	}

}