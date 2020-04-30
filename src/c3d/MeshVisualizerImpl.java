package c3d;

import java.util.List;

import c3d.geometry.*;

public class MeshVisualizerImpl implements MeshVisualizer {
	private Mesh mesh;
	private double rotationXY = 0;
	private double rotationYZ = 0;
	private Point3D translation = Point3D.origin();
	private static double targetMeshScale = 100;
	private static Point2D pointOfView = Point2D.fromDoubles(0, targetMeshScale / 2);

	@Override
	public void setModel(final List<Segment3D> model) {
		this.mesh = Mesh.fromSegments(model).scaled(targetMeshScale);
	}

	@Override
	public void setRotationXY(final double rotationXY) {
		this.rotationXY = rotationXY;
	}

	@Override
	public void setRotationYZ(final double rotationYZ) {
		this.rotationYZ = rotationYZ;
	}

	@Override
	public void setTranslation(final Point3D translation) {
		this.translation = translation;
	}

	@Override
	public List<Segment2D> render() {
		return this.mesh.render(new RenderParameters() {

			@Override
			public double rotationXY() {
				return MeshVisualizerImpl.this.rotationXY;
			}

			@Override
			public double rotationYZ() {
				return MeshVisualizerImpl.this.rotationYZ;
			}

			@Override
			public Point3D translation() {
				return MeshVisualizerImpl.this.translation;
			}

			@Override
			public Point2D pointOfView() {
				return MeshVisualizerImpl.pointOfView;
			}

		});
	}
}
