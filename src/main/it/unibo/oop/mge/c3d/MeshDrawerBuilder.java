package it.unibo.oop.mge.c3d;

import java.util.Collections;

import it.unibo.oop.mge.c3d.geometry.Point3D;

public interface MeshDrawerBuilder {

    MeshDrawerBuilder add(Mesh mesh);

    MeshDrawerBuilder rotationXY(double rotationXY);

    MeshDrawerBuilder rotationYZ(double rotationYZ);

    MeshDrawerBuilder translation(Point3D translation);

    MeshDrawer build();

    static MeshDrawerBuilder create() {
        return new MeshDrawerBuilderImpl(Collections.emptyList(), 0, 0, Point3D.origin());
    }

}
