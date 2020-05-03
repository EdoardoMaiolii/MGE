package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import c3d.Mesh;

public class MeshWriterImpl implements MeshWriter {

    private final Mesh mesh;

    @Override
    public final void write(final String path) throws IOException {

        // write to file
        Files.write(Paths.get(path), MeshYamlRepresentation.of(this.mesh).toYamlString().getBytes());
    }

    MeshWriterImpl(final Mesh mesh) {
        this.mesh = mesh;
    }

}
