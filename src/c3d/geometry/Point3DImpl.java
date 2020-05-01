package c3d.geometry;

import java.util.function.Function;

public class Point3DImpl implements Point3D {

	@Override
	public String toString() {
		return "Point3DImpl [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	private final double x;
	private final double y;
	private final double z;

	// packace protected
	Point3DImpl(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getZ() {
		return z;
	}

	private Point3DImpl rotatedXY(final double angle) {
		return new Point3DImpl(this.getX() * Math.cos(angle) - this.getY() * Math.sin(angle),
				this.getX() * Math.sin(angle) + this.getY() * Math.cos(angle), this.getZ());
	}

	private Point3DImpl rotatedYZ(final double angle) {
		return new Point3DImpl(this.getX(), this.getY() * Math.cos(angle) - this.getZ() * Math.sin(angle),
				this.getY() * Math.sin(angle) + this.getZ() * Math.cos(angle));
	}

	@Override
	public Point3D translated(final double x, final double y, final double z) {
		return new Point3DImpl(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Point3D rotated(final double angleXY, final double angleYZ) {
		return this.rotatedXY(angleXY).rotatedYZ(angleYZ);
	}

	@Override
	public Point3D transformed(Function<Double, Double> transformation) {
		return new Point3DImpl(transformation.apply(this.x), transformation.apply(this.y),
				transformation.apply(this.z));
	}

	@Override
	public Point3D translated(Point3D vector) {
		return new Point3DImpl(this.getX() + vector.getX(), this.getY() + vector.getY(), this.getZ() + vector.getZ());
	}

}
