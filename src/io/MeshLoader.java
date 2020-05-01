package io;

import c3d.Mesh;

public interface MeshLoader {
    Mesh load(String path);
}