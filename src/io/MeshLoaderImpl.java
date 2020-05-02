package io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;

import c3d.Mesh;
import c3d.geometry.Point3D;

public class MeshLoaderImpl implements MeshLoader{

  private final Map<String, Point3D> points = new HashMap<>();
  
  @Override
  public Mesh load(String path) {
    try {
      final YamlMapping mesh = Yaml.createYamlInput(new File(path)).readYamlMapping();
      //filling points map
      for(int i=0; i<mesh.yamlMapping("points").keys().size(); i++) {
        points.put(Integer.toString(i), makePoint(mesh.yamlMapping("points").yamlMapping(Integer.toString(i))));
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    return null;
  }

  private Point3D makePoint(final YamlMapping coords) {
    return Point3D.fromDoubles(coords.doubleNumber("x"), coords.doubleNumber("y"), coords.doubleNumber("z"));
  }
  
}
