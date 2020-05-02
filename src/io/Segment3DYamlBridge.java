package io;

import java.util.function.Function;

import com.amihaiemil.eoyaml.*;

import c3d.geometry.Point3D;
import c3d.geometry.Segment3D;

public class Segment3DYamlBridge {
    private final Segment3D segment;

    public static Segment3DYamlBridge of(final Segment3D segment) {
        return new Segment3DYamlBridge(segment);
    }

    private Segment3DYamlBridge(final Segment3D Segment) {
        this.segment = Segment;
    }

    YamlMapping segmentYaml(final Function<Point3D, String> mapping) {
        return Yaml.createYamlMappingBuilder().add("a", mapping.apply(segment.getA()))
                .add("b", mapping.apply(segment.getB()))
                .add("color", ColorYamlNodeConverter.of(segment.getColor()).colorYaml()).build();
    }

}