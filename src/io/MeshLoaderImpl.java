package io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;

import c3d.Mesh;
import c3d.geometry.Point3D;
import c3d.geometry.Segment2D;
import c3d.geometry.Segment3D;

public class MeshLoaderImpl implements MeshLoader{

  private final Map<String, Point3D> points = new HashMap<>();
  private final List<Segment3D> segments = new LinkedList<>();
  
  @Override
  public Mesh load(String path) {
    try {
      final YamlMapping mesh = Yaml.createYamlInput(new File(path)).readYamlMapping();
      // filling points map
      for (final YamlNode n : mesh.yamlMapping("points").keys()) {
        this.points.put(n.toString(), makePoint(mesh.yamlMapping("points").yamlMapping(n)));
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    return Mesh.fromSegments(this.segments);
  }

  private Point3D makePoint(final YamlMapping coords) {
    return Point3D.fromDoubles(coords.doubleNumber("x"), coords.doubleNumber("y"), coords.doubleNumber("z"));
  }
  
}
