package io;

import java.io.IOException;
import c3d.Mesh;

public interface MeshWriter {
    void write(String path) throws IOException;

    static MeshWriter fromMesh(Mesh mesh) {
        return new MeshWriterImpl(mesh);
    }
}
