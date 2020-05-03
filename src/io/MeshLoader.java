package io;

import java.io.FileNotFoundException;
import java.io.IOException;

import c3d.Mesh;

public interface MeshLoader {
    Mesh load(String path) throws FileNotFoundException, IOException;
}