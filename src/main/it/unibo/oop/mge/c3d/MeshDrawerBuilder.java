package it.unibo.oop.mge.c3d;

import java.util.Collection;
import java.util.Collections;

import it.unibo.oop.mge.c3d.geometry.Point3D;

public interface MeshDrawerBuilder {

    static MeshDrawerBuilder create() {
        return new MeshDrawerBuilderImpl(Collections.emptyList(), 0, 0, Point3D.origin());
    }

    MeshDrawerBuilder add(Mesh mesh);

    MeshDrawerBuilder addAll(Collection<Mesh> meshes);

    MeshDrawer build();

    MeshDrawerBuilder rotationXY(double rotationXY);

    MeshDrawerBuilder rotationYZ(double rotationYZ);

    MeshDrawerBuilder translation(Point3D translation);

}
