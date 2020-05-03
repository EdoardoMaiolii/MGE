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
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;

import it.unibo.oop.mge.c3d.Mesh;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

public class MeshLoaderImpl implements MeshLoader {

    private final Map<YamlNode, Point3D> points = new HashMap<>();
    private final List<Segment3D> segments = new LinkedList<>();

    @Override
    public final Mesh load(final String path) throws FileNotFoundException, IOException {
        final YamlMapping mesh = Yaml.createYamlInput(new File(path)).readYamlMapping();
        final YamlMapping yamlPoints = mesh.yamlMapping("points");
        final YamlSequence yamlSegments = mesh.yamlSequence("segments");
        yamlPoints.keys().stream().forEach(e -> this.points.put(e, toPoint(yamlPoints.yamlMapping(e))));
        for (int i = 0; i < yamlSegments.size(); i++) {
            final Point3D pointA = this.points.get(yamlSegments.yamlMapping(i).value("a"));
            final Point3D pointB = this.points.get(yamlSegments.yamlMapping(i).value("b"));
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
