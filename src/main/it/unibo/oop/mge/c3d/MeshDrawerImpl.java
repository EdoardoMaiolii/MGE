package it.unibo.oop.mge.c3d;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

public class MeshDrawerImpl implements MeshDrawer {
    private static final double TARGET_MESH_SCALE = 100;
    private static final Point2D POINT_OF_VIEW = Point2D.fromDoubles(0, -2 * TARGET_MESH_SCALE);
    private final List<Mesh> meshes;
    private final double rotationXY;
    private final double rotationYZ;
    private final Point3D translation;

    MeshDrawerImpl(final List<Mesh> meshes, final double rotationXY, final double rotationYZ,
            final Point3D translation) {

        super();
        final double scale = meshes.stream().mapToDouble(mesh -> mesh.getScale()).max().orElse(1);
        this.meshes = meshes.stream().map((Mesh mesh) -> mesh.transformed(value -> value / scale * TARGET_MESH_SCALE))
                .collect(Collectors.toList());
        this.rotationXY = rotationXY;
        this.rotationYZ = rotationYZ;
        this.translation = translation;
    }

    private Optional<Point2D> processPoint(final Point3D point) {
        final var a = point.rotated(rotationXY, rotationYZ).translated(translation);
        if (this.validPoint(a)) {
            return Optional.of(Point3DRenderer.fromPoint(a).render(POINT_OF_VIEW));
        }
        return Optional.empty();
    }

    private Optional<Segment2D> processSegment(final Segment3D segment) {

        final var a = processPoint(segment.getA());
        final var b = processPoint(segment.getB());

        if (a.isPresent() && b.isPresent()) {
            return Optional.of(Segment2D.fromPoints(a.get(), b.get(), segment.getColor()));
        } else {
            return Optional.empty();
        }

    }

    @Override
    public final Mesh2D render() {
        return Mesh2D.of(this.meshes.stream().flatMap(mesh -> mesh.getSegments().stream()).map(this::processSegment)
                .flatMap(Optional::stream).map((Segment2D seg) -> seg.transformed(coord -> coord / TARGET_MESH_SCALE))
                .collect(Collectors.toList()));

    }

    private boolean validPoint(final Point3D point) {
        return point.getY() > POINT_OF_VIEW.getY();
    }
}
