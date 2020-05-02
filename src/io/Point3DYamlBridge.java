package io;

import com.amihaiemil.eoyaml.*;

import c3d.geometry.Point3D;

public class Point3DYamlBridge {
    private final Point3D point;

    public static Point3DYamlBridge of(final Point3D point) {
        return new Point3DYamlBridge(point);
    }

    private Point3DYamlBridge(final Point3D point) {
        this.point = point;
    }

    YamlMapping pointYaml() {
        return Yaml.createYamlMappingBuilder().add("x", Double.toString(point.getX()))
                .add("y", Double.toString(point.getY())).add("z", Double.toString(point.getZ())).build();
    }

}