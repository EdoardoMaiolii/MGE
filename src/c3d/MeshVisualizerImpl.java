package c3d;

import java.util.List;
import java.util.stream.Collectors;

import c3d.geometry.Point2D;
import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public class MeshVisualizerImpl implements MeshVisualizer {
    private Mesh mesh;
    private double rotationXY;
    private double rotationYZ;
    private Point3D translation = Point3D.origin();
    private static double targetMeshScale = 100;
    private static Point2D pointOfView = Point2D.fromDoubles(0, -targetMeshScale);

    @Override
    public final void setMesh(final List<Segment3D> segments) {
        this.mesh = Mesh.fromSegments(segments);
        final double currentScale = mesh.getScale();
        this.mesh = this.mesh.transformed(value -> value / currentScale * targetMeshScale)
                .translated(Point3D.fromDoubles(0, targetMeshScale, 0));
    }

    @Override
    public final void setRotationXY(final double rotationXY) {
        this.rotationXY = rotationXY;
    }

    @Override
    public final void setRotationYZ(final double rotationYZ) {
        this.rotationYZ = rotationYZ;
    }

    @Override
    public final void setTranslation(final Point3D translation) {
        this.translation = translation;
    }

    @Override
    public final List<Segment2D> render() {
        return MeshRenderer.fromMesh(this.mesh).render(new RenderParameters() {

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

        }).stream().map((Segment2D seg) -> seg.transformed(coord -> coord / targetMeshScale))
                .collect(Collectors.toList());
    }

    @Override
    public final double getRotationXY() {
        return this.rotationXY;
    }

    @Override
    public final double getRotationYZ() {
        return this.rotationYZ;
    }

    @Override
    public final Point3D getTranslation() {
        return this.translation;
    }
}
