package c3d;

import java.util.List;

import c3d.geometry.*;

public class ModelVisualizerImpl implements ModelVisualizer {
	private Mesh model;
	private double rotationXY = 0;
	private double rotationYZ = 0;
	private Point3D translation = Point3D.origin();
	private static double targetMeshScale = 60;

	@Override
	public void setModel(final List<Segment3D> model) {
		this.model = Mesh.fromSegments(model).scaled(targetMeshScale);
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
		return this.model.render(new RenderParameters() {

			@Override
			public double rotationXY() {
				return ModelVisualizerImpl.this.rotationXY;
			}

			@Override
			public double rotationYZ() {
				return ModelVisualizerImpl.this.rotationYZ;
			}

			@Override
			public Point3D translation() {
				return ModelVisualizerImpl.this.translation;
			}

		});
	}
}
