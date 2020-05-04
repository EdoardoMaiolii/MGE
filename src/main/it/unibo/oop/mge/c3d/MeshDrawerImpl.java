package it.unibo.oop.mge.c3d;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment2D;

public class MeshDrawerImpl implements MeshDrawer {
    private final List<Mesh> meshes;
    private final double rotationXY;
    private final double rotationYZ;
    private final Point3D translation;
    private static double targetMeshScale = 100;
    private static Point2D pointOfView = Point2D.fromDoubles(0, -targetMeshScale);

    MeshDrawerImpl(final List<Mesh> meshes, final double rotationXY, final double rotationYZ,
            final Point3D translation) {
        super();
        this.meshes = meshes.stream().map(this::transformMesh).collect(Collectors.toList());
        this.rotationXY = rotationXY;
        this.rotationYZ = rotationYZ;
        this.translation = translation;
    }

    private Mesh transformMesh(final Mesh mesh) {
        final double currentScale = mesh.getScale();
        return mesh.transformed(value -> value / currentScale * targetMeshScale)
                .translated(Point3D.fromDoubles(0, targetMeshScale, 0));
    }

    @Override
    public final Mesh2D render() {

        return Mesh2D
                .of(this.meshes.stream().map(MeshRenderer::fromMesh).flatMap(el -> el.render(new RenderParameters() {

                    @Override
                    public double rotationXY() {
                        return MeshDrawerImpl.this.rotationXY;
                    }

                    @Override
                    public double rotationYZ() {
                        return MeshDrawerImpl.this.rotationYZ;
                    }

                    @Override
                    public Point3D translation() {
                        return MeshDrawerImpl.this.translation;
                    }

                    @Override
                    public Point2D pointOfView() {
                        return MeshDrawerImpl.pointOfView;
                    }

                }).stream()).map((Segment2D seg) -> seg.transformed(coord -> coord / targetMeshScale))
                        .collect(Collectors.toList()));

    }
}
