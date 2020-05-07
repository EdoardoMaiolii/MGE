package it.unibo.oop.mge.io;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;

import it.unibo.oop.mge.c3d.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

public class MeshLoaderImpl implements MeshLoader {

    private final Map<String, Point3D> points = new HashMap<>();
    private final List<Segment3D> segments = new LinkedList<>();

    @Override
    public final Mesh load(final String path) throws FileNotFoundException, IOException {
        final YamlMapping mesh = Yaml.createYamlInput(new File(path)).readYamlMapping();
        final YamlMapping yamlPoints = mesh.yamlMapping("points");
        final YamlSequence yamlSegments = mesh.yamlSequence("segments");
        for (int i = 0; i < yamlSegments.size(); i++) {
            final YamlMapping yamlSegment = yamlSegments.yamlMapping(i);
            final Point3D pointA = this.points.computeIfAbsent(yamlSegment.string("a"),
                    e -> toPoint(yamlPoints.yamlMapping(yamlSegment.string("a"))));
            final Point3D pointB = this.points.computeIfAbsent(yamlSegment.string("b"),
                    e -> toPoint(yamlPoints.yamlMapping(yamlSegment.string("b"))));
            final Color color = toColor(yamlSegments.yamlMapping(i).yamlMapping("color"));
            this.segments.add(Segment3D.fromPoints(pointA, pointB, color));
        }
        return Mesh.fromSegments(this.segments);
    }

    private Point3D toPoint(final YamlMapping coords) {
        return Point3D.fromDoubles(coords.doubleNumber("x"), coords.doubleNumber("y"), coords.doubleNumber("z"));
    }

    private Color toColor(final YamlMapping color) {
        return new Color(color.integer("r"), color.integer("g"), color.integer("b"));
    }
}
