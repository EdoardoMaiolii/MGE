package c3d;

import java.util.*;
import java.util.stream.*;

import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public class MeshImpl implements Mesh {
	private final List<Segment3D> segments;

	MeshImpl(final List<Segment3D> segments) {
		this.segments = segments;
	}

	private double getScale() {
		return segments.stream().flatMap(seg -> Stream.of(seg.getA(), seg.getB()))
				.flatMapToDouble(point -> DoubleStream.of(point.getX(), point.getY())).max().orElse(1);
	}

	@Override
	public Mesh scaled(final double scale) {
		final double currentScale = this.getScale();
		return new MeshImpl(
				segments.stream().map(seg -> Segment3D.fromPoints(seg.getA().changeScale(currentScale, scale),
						seg.getB().changeScale(currentScale, scale))).collect(Collectors.toList()));
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
}