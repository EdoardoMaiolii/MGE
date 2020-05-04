package it.unibo.oop.mge.c3d;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

class MeshDrawerBuilderTest {
    final Point3D a = Point3D.fromDoubles(-100, -100, 0);
    final Point3D b = Point3D.fromDoubles(100, -100, 0);
    final Point3D c = Point3D.fromDoubles(-100, 100, 0);
    final Point3D d = Point3D.fromDoubles(100, 100, 0);

    final Segment3D ab = Segment3D.fromPoints(a, b);
    final Segment3D bd = Segment3D.fromPoints(b, d, Color.RED);
    final Segment3D cd = Segment3D.fromPoints(c, d, Color.GREEN);
    final Segment3D ca = Segment3D.fromPoints(c, a);

    final List<Segment3D> set = Arrays.asList(ab, bd, cd, ca);

    final Mesh mesh = Mesh.fromSegments(set);

    @Test
    void test() {
        var a = MeshDrawerBuilder.create().add(mesh).add(mesh).build().render();
    }

}
