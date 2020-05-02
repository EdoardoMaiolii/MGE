package io;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.*;
import com.amihaiemil.eoyaml.*;

import c3d.Mesh;
import c3d.geometry.Point3D;
import c3d.geometry.Segment3D;

public class MeshYamlRepresentation {
    private final Mesh mesh;

    public static MeshYamlRepresentation of(final Mesh mesh) {
        return new MeshYamlRepresentation(mesh);
    }

    private MeshYamlRepresentation(final Mesh mesh) {
        this.mesh = mesh;
    }

    String toYamlString() {
        // set up data for later steps
        final List<Point3D> uniquePoints = mesh.getSegments().stream()
                .flatMap((final Segment3D seg) -> Stream.of(seg.getA(), seg.getB())).distinct()
                .collect(Collectors.toList());
        final Map<Point3D, String> pointToNames = IntStream.range(0, uniquePoints.size()).boxed()
                .collect(Collectors.toMap(i -> uniquePoints.get(i), i -> Integer.toString(i)));

        // build points node
        var pointsNodeBuilder = Yaml.createYamlMappingBuilder();
        for (int i = 0; i < uniquePoints.size(); i++) {
            pointsNodeBuilder = pointsNodeBuilder.add(Integer.toString(i),
                    Point3DYamlBridge.of(uniquePoints.get(i)).pointYaml());
        }
        final var pointsNode = pointsNodeBuilder.build();

        // build segments node
        final Set<YamlNode> segments = mesh.getSegments().stream()
                .map(seg -> Segment3DYamlBridge.of(seg).segmentYaml(pointToNames::get))
                .collect(Collectors.toSet());
        var segmentsNodeBuilder = Yaml.createYamlSequenceBuilder();
        for (final YamlNode seg : segments) {
            segmentsNodeBuilder = segmentsNodeBuilder.add(seg);
        }
        final var segmentsNode = segmentsNodeBuilder.build();

        // build complete node
        final YamlNode completeNode = Yaml.createYamlMappingBuilder().add("points", pointsNode)
                .add("segments", segmentsNode).build();

        return completeNode.toString();
    }
}