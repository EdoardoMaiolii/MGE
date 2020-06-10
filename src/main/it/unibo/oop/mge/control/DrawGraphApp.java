package it.unibo.oop.mge.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.mge.c3d.Mesh;
import it.unibo.oop.mge.c3d.MeshDrawerBuilder;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.color.VariableColorBuilderImpl;
import it.unibo.oop.mge.io.MeshLoader;
import it.unibo.oop.mge.io.MeshLoaderImpl;
import it.unibo.oop.mge.io.MeshWriter;
import it.unibo.oop.mge.libraries.Constant;
import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.libraries.Properties;
import it.unibo.oop.mge.libraries.Variable;
import it.unibo.oop.mge.model.FunctionFeaturesBuilderImpl;
import it.unibo.oop.mge.model.FunctionFeaturesImpl;
import it.unibo.oop.mge.model.StringComposer;
import it.unibo.oop.mge.view.DrawGraphView;
import it.unibo.oop.mge.view.DrawGraphViewImpl;

public class DrawGraphApp implements DrawGraphViewObserver {

    private final DrawGraphView view;
    private Point3D visualizerTranslation = Point3D.origin();
    private double visualizerRotationXY;
    private double visualizerRotationYZ;
    private final List<Mesh> visualizerMeshes;
    private static double rotationDelta = 15 * Math.PI / 180;

    public DrawGraphApp() {
        this.visualizerMeshes = new ArrayList<>();
        this.view = new DrawGraphViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public final void newGraph(final String function, final double max, final double min, final double rate) {
        boolean creationSuccess = false;
        VariableColor color = new VariableColorBuilderImpl().setBlue(103).setGreen(99).build();
        FunctionFeaturesImpl functionFeatures = new FunctionFeaturesBuilderImpl()
                .setFunction(StringComposer.parse(function)).setIntervals(min, max).setRate(rate).setDinamicColor(color)
                .setDecimalPrecision(4).build();
        this.visualizerMeshes.add(Mesh.fromSegments(functionFeatures.getPolygonalModel()));
        this.visualizerMeshes.add(Mesh.fromSegments(functionFeatures.getPolygonalAxis()));
        this.view.plotProperties(
                List.of(Properties.MAX.getSyntax() + " " + functionFeatures.getPointOfAbsoluteMax().toString(),
                        Properties.MIN.getSyntax() + " " + functionFeatures.getPointOfAbsoluteMin().toString()));
        creationSuccess = true;

        if (creationSuccess) {
            this.refreshVisualizer();
        }

    }

    public static void main(final String[] args) {
        new DrawGraphApp();
    }

    @Override
    public void load(final String path) {
        final MeshLoader meshLoader = new MeshLoaderImpl();
        try {
            this.visualizerMeshes.add(meshLoader.load(path));
            this.refreshVisualizer();
        } catch (IOException e) {
            this.view.ioError();
        }
    }

    @Override
    public void save(final String path) {
        try {
            MeshWriter.fromMesh(Mesh.fromMeshes(visualizerMeshes)).write(path);
        } catch (IOException e) {
            this.view.ioError();
        }
    }

    @Override
    public final void zoomIn() {
        this.visualizerTranslation = this.visualizerTranslation.translated(Point3D.fromDoubles(0, -10, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void zoomOut() {
        this.visualizerTranslation = this.visualizerTranslation.translated(Point3D.fromDoubles(0, 10, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void moveUp() {
        this.visualizerTranslation = this.visualizerTranslation.translated(Point3D.fromDoubles(0, 0, 10));
        this.refreshVisualizer();

    }

    @Override
    public final void moveLeft() {
        this.visualizerTranslation = this.visualizerTranslation.translated(Point3D.fromDoubles(-10, 0, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void moveRight() {
        this.visualizerTranslation = this.visualizerTranslation.translated(Point3D.fromDoubles(10, 0, 0));
        this.refreshVisualizer();

    }

    @Override
    public final void moveDown() {
        this.visualizerTranslation = this.visualizerTranslation.translated(Point3D.fromDoubles(0, 0, -10));
        this.refreshVisualizer();
    }

    @Override
    public final void increaseXY() {
        this.visualizerRotationXY += rotationDelta;
        this.refreshVisualizer();

    }

    @Override
    public final void decreaseXY() {
        this.visualizerRotationXY -= rotationDelta;
        this.refreshVisualizer();

    }

    @Override
    public final void increaseYZ() {
        this.visualizerRotationYZ += rotationDelta;
        this.refreshVisualizer();

    }

    @Override
    public final void decreaseYZ() {
        this.visualizerRotationYZ -= rotationDelta;
        this.refreshVisualizer();

    }

    @Override
    public void quit() {
        System.exit(0);
    }

    private void refreshVisualizer() {
        this.view.plotGraph(MeshDrawerBuilder.create().addAll(visualizerMeshes).translation(visualizerTranslation)
                .rotationXY(visualizerRotationXY).rotationYZ(visualizerRotationYZ).build().render().getSegments());
    }
}
