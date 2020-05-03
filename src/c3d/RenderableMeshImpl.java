package c3d;

import java.util.List;
import java.util.stream.Collectors;

import c3d.geometry.Segment2D;

public class RenderableMeshImpl implements MeshRenderer {

    private Mesh mesh;

    @Override
    public List<Segment2D> render(RenderParameters params) {
        return this.mesh.getSegments().stream()
                .map(seg -> seg.rotated(params.rotationXY(), params.rotationYZ()))
                .map(seg -> seg.translated(params.translation()))
                .map(seg -> Segment2D.fromPoints(
                        Point3DRenderer.fromPoint(seg.getA()).render(params.pointOfView()),
                        Point3DRenderer.fromPoint(seg.getB()).render(params.pointOfView()),
                        seg.getColor()))
                .collect(Collectors.toList());
    }

    RenderableMeshImpl(Mesh mesh) {
        this.mesh = mesh;
    }

}
