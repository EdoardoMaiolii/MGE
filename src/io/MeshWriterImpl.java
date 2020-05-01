package io;

import c3d.Mesh;
import c3d.geometry.*;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.*;
import java.util.*;

import com.amihaiemil.eoyaml.*;

public class MeshWriterImpl implements MeshWriter {

        @Override
        public void write(final Mesh mesh, final String path) throws IOException {
                final List<Point3D> points = mesh.getSegments().stream()
                                .flatMap((final Segment3D seg) -> Stream.of(seg.getA(), seg.getB()))
                                .distinct().collect(Collectors.toList());
                final Map<Point3D, String> pointMap =
                                IntStream.range(0, points.size()).boxed().collect(Collectors.toMap(
                                                i -> points.get(i), i -> Integer.toString(i)));

                final var pointsNodeBuilder = Yaml.createYamlSequenceBuilder();
                IntStream.range(0, points.size())
                                .mapToObj(i -> this.pointYaml(points.get(i), Integer.toString(i)))
                                .forEach(i -> pointsNodeBuilder.add(i));

                final YamlNode pointsNode = pointsNodeBuilder.build();

                final var segmentsNodeBuilder = Yaml.createYamlSequenceBuilder();
                mesh.getSegments().stream().map(seg -> this.segmentYaml(seg, pointMap::get))
                                .forEach(node -> segmentsNodeBuilder.add(node));

                final YamlNode segmentsNode = segmentsNodeBuilder.build();

                final YamlNode completeNode = Yaml.createYamlMappingBuilder()
                                .add("points", pointsNode).add("segments", segmentsNode).build();

                Files.write(Paths.get(path), completeNode.toString().getBytes());
        }

        YamlNode pointYaml(final Point3D point, final String name) {
                return Yaml.createYamlMappingBuilder().add("name", name)
                                .add("x", Double.toString(point.getX()))
                                .add("y", Double.toString(point.getY()))
                                .add("z", Double.toString(point.getZ())).build();
        }

        YamlNode segmentYaml(final Segment3D segment, Function<Point3D, String> mapping) {
                return Yaml.createYamlMappingBuilder().add("a", mapping.apply(segment.getA()))
                                .add("b", mapping.apply(segment.getB()))
                                .add("color", this.colorYaml(segment.getColor())).build();
        }

        YamlNode colorYaml(Color color) {
                return Yaml.createYamlMappingBuilder().add("r", Integer.toString(color.getRed()))
                                .add("g", Integer.toString(color.getGreen()))
                                .add("b", Integer.toString(color.getBlue())).build();
        }

}
