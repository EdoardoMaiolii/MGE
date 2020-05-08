package it.unibo.oop.mge.io;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.c3d.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

import java.awt.Color;
import java.io.IOException;

class MeshWriterTest {

    private final Point3D a = Point3D.fromDoubles(-100, -100, 0);
    private final Point3D b = Point3D.fromDoubles(100, -100, 0);
    private final Point3D c = Point3D.fromDoubles(-100, 100, 0);
    private final Point3D d = Point3D.fromDoubles(100, 100, 0);

    private final Segment3D ab = Segment3D.fromPoints(a, b, Color.RED);
    private final Segment3D bd = Segment3D.fromPoints(b, d);
    private final Segment3D cd = Segment3D.fromPoints(c, d);
    private final Segment3D ca = Segment3D.fromPoints(c, a);

    private final List<Segment3D> set = Arrays.asList(ab, bd, cd, ca);

    private final Mesh mesh = Mesh.fromSegments(set);

    @Test
    public void test() throws IOException {

        final MeshWriter writer = MeshWriter.fromMesh(mesh);
        writer.write("C:\\users\\eliau\\desktop\\test.yml");
    }

    @Test
    public void test2() {
        System.out.println(MeshYamlRepresentation.of(mesh).toYamlString());
    }
}
