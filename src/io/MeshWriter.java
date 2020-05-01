package io;

import java.io.IOException;
import c3d.Mesh;

public interface MeshWriter {
    void write(Mesh mesh, String path) throws IOException;
}
