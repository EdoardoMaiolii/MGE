package c3d;

import java.util.List;
import java.util.stream.Collectors;

import c3d.geometry.Segment2D;

public class RenderableMeshImpl implements RenderableMesh {

    private Mesh mesh;

    @Override
    public List<Segment2D> render(RenderParameters params) {
        return this.mesh.getSegments().stream().map(seg -> seg.rotated(params.rotationXY(), params.rotationYZ()))
                .map(seg -> seg.translated(params.translation().getX(), params.translation().getY(),
                        params.translation().getZ()))
                .map(seg -> seg.render()).collect(Collectors.toList());
    }

    RenderableMeshImpl(Mesh mesh) {
        this.mesh = mesh;
    }

}