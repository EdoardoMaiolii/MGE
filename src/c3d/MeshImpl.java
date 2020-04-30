package c3d;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public class MeshImpl implements Mesh {
	private final List<Segment3D> segments;

	MeshImpl(final List<Segment3D> segments) {
		this.segments = segments;
	}

	@Override
	public double getScale() {
		return segments.stream().flatMap(seg -> Stream.of(seg.getA(), seg.getB()))
				.flatMapToDouble(point -> DoubleStream.of(point.getX(), point.getY())).max().orElse(1);
	}

	@Override
	public List<Segment2D> render(final RenderParameters params) {
		return this.segments.stream().map(seg -> seg.rotated(params.rotationXY(), params.rotationYZ())).map(seg -> seg
				.translated(params.translation().getX(), params.translation().getY(), params.translation().getZ()))
				.map(seg -> seg.render()).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "MeshImpl [segments=" + segments + "]";
	}

	@Override
	public Mesh transformed(final Function<Double, Double> transformation) {
		return new MeshImpl(
				this.segments.stream().map(seg -> seg.transformed(transformation)).collect(Collectors.toList()));
	}

	@Override
	public Mesh translated(Point3D vector) {
		return new MeshImpl(this.segments.stream()
				.map(seg -> seg.translated(vector.getX(), vector.getY(), vector.getZ())).collect(Collectors.toList()));
	}
}