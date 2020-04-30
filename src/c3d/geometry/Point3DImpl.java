package c3d.geometry;

public class Point3DImpl implements Point3D {

	private final double x;
	private final double y;
	private final double z;
	private Point2D cachedRender;

	private static final Point2D pointOfView = Point2D.fromDoubles(0, -600);
	private static final Point2D focalPoint = Point2D.origin();

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
	public Point3D translate(final double x, final double y, final double z) {
		return new Point3DImpl(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Point3D rotated(final double angleXY, final double angleYZ) {
		return this.rotatedXY(angleXY).rotatedYZ(angleYZ);
	}

	@Override
	public Point2D render() {
		if (this.cachedRender == null) {
			final double finalX = Line.fromPoints(Point2D.fromDoubles(this.getX(), this.getY()), pointOfView).getZero();

			final Line finalLine = Line.fromPoints(Point2D.fromDoubles(this.getX(), this.getZ()), focalPoint);
			final double finalY = finalLine.solveFor(finalX);

			this.cachedRender = Point2D.fromDoubles(finalX, finalY);
		}
		return this.cachedRender;
	}

}
