package it.unibo.oop.mge.io;

import java.io.IOException;

import it.unibo.oop.mge.c3d.Mesh;

public interface MeshWriter {
    void write(String path) throws IOException;

    static MeshWriter fromMesh(Mesh mesh) {
        return new MeshWriterImpl(mesh);
    }
}
