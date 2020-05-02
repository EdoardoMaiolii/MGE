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

    private final Mesh mesh;

    @Override
    public void write(final String path) throws IOException {
        final List<Point3D> uniquePoints = mesh.getSegments().stream()
                .flatMap((final Segment3D seg) -> Stream.of(seg.getA(), seg.getB())).distinct()
                .collect(Collectors.toList());
        final Map<Point3D, String> pointToNames = IntStream.range(0, uniquePoints.size()).boxed()
                .collect(Collectors.toMap(i -> uniquePoints.get(i), i -> Integer.toString(i)));

        final var pointsNodeBuilder = Yaml.createYamlSequenceBuilder();
        IntStream.range(0, uniquePoints.size()).mapToObj(i -> this.pointYaml(uniquePoints.get(i), Integer.toString(i)))
                .peek(i -> System.out.println(i)).forEach(i -> pointsNodeBuilder.add(i));
        final YamlNode pointsNode = pointsNodeBuilder.build();

        final var segmentsNodeBuilder = Yaml.createYamlSequenceBuilder();
        mesh.getSegments().stream().map(seg -> this.segmentYaml(seg, pointToNames::get))
                .forEach(node -> segmentsNodeBuilder.add(node));

        final YamlNode segmentsNode = segmentsNodeBuilder.build();

        final YamlNode completeNode = Yaml.createYamlMappingBuilder().add("points", pointsNode)
                .add("segments", segmentsNode).build();

        Files.write(Paths.get(path), completeNode.toString().getBytes());
    }

    YamlNode pointYaml(final Point3D point, final String name) {
        return Yaml.createYamlMappingBuilder().add("name", name).add("x", Double.toString(point.getX()))
                .add("y", Double.toString(point.getY())).add("z", Double.toString(point.getZ())).build();
    }

    YamlNode segmentYaml(final Segment3D segment, final Function<Point3D, String> mapping) {
        return Yaml.createYamlMappingBuilder().add("a", mapping.apply(segment.getA()))
                .add("b", mapping.apply(segment.getB())).add("color", this.colorYaml(segment.getColor())).build();
    }

    YamlNode colorYaml(final Color color) {
        return Yaml.createYamlMappingBuilder().add("r", Integer.toString(color.getRed()))
                .add("g", Integer.toString(color.getGreen())).add("b", Integer.toString(color.getBlue())).build();
    }

    MeshWriterImpl(final Mesh mesh) {
        this.mesh = mesh;
    }

}
