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
        // set up data for later steps
        final List<Point3D> uniquePoints = mesh.getSegments().stream()
                .flatMap((final Segment3D seg) -> Stream.of(seg.getA(), seg.getB())).distinct()
                .collect(Collectors.toList());
        final Map<Point3D, String> pointToNames = IntStream.range(0, uniquePoints.size()).boxed()
                .collect(Collectors.toMap(i -> uniquePoints.get(i), i -> Integer.toString(i)));

        // build points node
        var pointsNodeBuilder = Yaml.createYamlMappingBuilder();
        for (int i = 0; i < uniquePoints.size(); i++) {
            pointsNodeBuilder = pointsNodeBuilder.add(Integer.toString(i), this.pointYaml(uniquePoints.get(i)));
        }
        final var pointsNode = pointsNodeBuilder.build();

        // build segments node
        final Set<YamlNode> segments = mesh.getSegments().stream().map(seg -> this.segmentYaml(seg, pointToNames::get))
                .collect(Collectors.toSet());
        var segmentsNodeBuilder = Yaml.createYamlSequenceBuilder();
        for (final YamlNode seg : segments) {
            segmentsNodeBuilder = segmentsNodeBuilder.add(seg);
        }
        final var segmentsNode = segmentsNodeBuilder.build();

        // build complete node
        final YamlNode completeNode = Yaml.createYamlMappingBuilder().add("points", pointsNode)
                .add("segments", segmentsNode).build();

        // write to file
        Files.write(Paths.get(path), completeNode.toString().getBytes());
    }

    YamlMapping pointYaml(final Point3D point) {
        return Yaml.createYamlMappingBuilder().add("x", Double.toString(point.getX()))
                .add("y", Double.toString(point.getY())).add("z", Double.toString(point.getZ())).build();
    }

    YamlMapping segmentYaml(final Segment3D segment, final Function<Point3D, String> mapping) {
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
