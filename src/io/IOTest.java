package io;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import c3d.Mesh;
import c3d.geometry.Point3D;
import c3d.geometry.Segment3D;
import java.awt.Color;
import java.io.IOException;

class IOTest {

    @Test
    void test() throws IOException {

        Point3D a = Point3D.fromDoubles(-100, -100, 0);
        Point3D b = Point3D.fromDoubles(100, -100, 0);
        Point3D c = Point3D.fromDoubles(-100, 100, 0);
        Point3D d = Point3D.fromDoubles(100, 100, 0);

        Segment3D ab = Segment3D.fromPoints(a, b, Color.RED);
        Segment3D bd = Segment3D.fromPoints(b, d);
        Segment3D cd = Segment3D.fromPoints(c, d);
        Segment3D ca = Segment3D.fromPoints(c, a);

        var set = Arrays.asList(ab, bd, cd, ca);

        MeshWriter writer = MeshWriter.fromMesh(Mesh.fromSegments(set));
        //writer.write("C:\\users\\eliau\\desktop\\test.yml");
        MeshLoader loader = new MeshLoaderImpl();
        loader.load("C:\\users\\eliau\\desktop\\test.yml");
    }
}

