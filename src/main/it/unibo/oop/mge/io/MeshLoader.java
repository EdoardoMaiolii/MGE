package it.unibo.oop.mge.io;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.unibo.oop.mge.c3d.Mesh;

public interface MeshLoader {
    Mesh load(String path) throws FileNotFoundException, IOException;
}
