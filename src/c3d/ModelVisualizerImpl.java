package c3d;

import java.util.List;
import java.util.stream.Collectors;

import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public class ModelVisualizerImpl implements ModelVisualizer {
	private List<Segment3D> model;
	private double rotationXY = 0;
	private double rotationYZ = 0;
	private Point3D translation = Point3D.origin();

	@Override
	public void setModel(List<Segment3D> model) {
		this.model = model;
	}

	@Override
	public void setRotationXY(double rotationXY) {
		this.rotationXY = rotationXY;
	}

	@Override
	public void setRotationYZ(double rotationYZ) {
		this.rotationYZ = rotationYZ;
	}

	@Override
	public void setTranslation(Point3D translation) {
		this.translation = translation;
	}

	@Override
	public List<Segment2D> render() {
		return this.model.stream()
				.map(el -> el.translated(this.translation.getX(), this.translation.getY(), this.translation.getZ()))
				.map(el -> el.rotated(this.rotationXY, this.rotationYZ)).map(el -> el.render())
				.collect(Collectors.toList());
	}
}
