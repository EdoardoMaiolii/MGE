package it.unibo.oop.mge.c3d;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment2D;

public class MeshDrawerImpl implements MeshDrawer {
    private final List<Mesh> meshes;
    private static double targetMeshScale = 100;
    private static Point2D pointOfView = Point2D.fromDoubles(0, -2 * targetMeshScale);
    private final Function<Point3D, Point2D> renderPoint;

    MeshDrawerImpl(final List<Mesh> meshes, final double rotationXY, final double rotationYZ,
            final Point3D translation) {

        super();
        final double scale = meshes.stream().mapToDouble(mesh -> mesh.getScale()).max().orElse(1);
        this.meshes = meshes.stream().map((Mesh mesh) -> mesh.transformed(value -> value / scale * targetMeshScale))
                .collect(Collectors.toList());
        this.renderPoint = (point) -> Point3DRenderer
                .fromPoint(point.rotated(rotationXY, rotationYZ).translated(translation)).render(pointOfView);
    }

    @Override
    public final Mesh2D render() {
        return Mesh2D.of(this.meshes.stream().flatMap(mesh -> mesh.getSegments().stream())
                .map(seg -> Segment2D.fromPoints(renderPoint.apply(seg.getA()), renderPoint.apply(seg.getB()),
                        seg.getColor()))
                .map((Segment2D seg) -> seg.transformed(coord -> coord / targetMeshScale))
                .collect(Collectors.toList()));

    }
}
