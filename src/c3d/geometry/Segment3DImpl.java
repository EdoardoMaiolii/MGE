package c3d.geometry;

import java.awt.Color;

public class Segment3DImpl implements Segment3D {
	@Override
	public String toString() {
		return "Segment3DImpl [a=" + a + ", b=" + b + ", color=" + color + "]";
	}

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
	Segment3DImpl(final Point3D a, final Point3D b, final Color color) {
		this.a = a;
		this.b = b;
		this.color = color;
	}

	Segment3DImpl(final Point3D a, final Point3D b) {
		this(a, b, Color.black);
	}

	@Override
	public Segment3D translated(final double x, final double y, final double z) {
		return new Segment3DImpl(this.getA().translate(x, y, z), this.getB().translate(x, y, z));
	}

	@Override
	public Segment3D rotated(final double angleXY, final double angleYZ) {
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
