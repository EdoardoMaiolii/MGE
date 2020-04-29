package c3d.geometry;

import java.awt.Color;

public class Segment3DImpl implements Segment3D {
	private final Point3D a;
	private final Point3D b;
	private final Color color;

	@Override
	public Point3D getA() {
		return a;
	}

	@Override
	public Point3D getB() {
		return b;
	}

	// package proteted
	Segment3DImpl(Point3D a, Point3D b, Color color) {
		this.a = a;
		this.b = b;
		this.color = color;
	}

	Segment3DImpl(Point3D a, Point3D b) {
		this(a, b, Color.black);
	}

	@Override
	public Segment3D translated(double x, double y, double z) {
		return new Segment3DImpl(this.getA().translate(x, y, z), this.getB().translate(x, y, z));
	}

	@Override
	public Segment3D rotated(double angleXY, double angleYZ) {
		return new Segment3DImpl(this.getA().rotated(angleXY, angleYZ), this.getB().rotated(angleXY, angleYZ));
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Segment2D render() {
		return Segment2D.fromPoints(this.getA().render(), this.getB().render(), this.color);
	}
}
