package io;

import c3d.Mesh;

public interface MeshWriter {
    void write(Mesh mesh, String path);
}