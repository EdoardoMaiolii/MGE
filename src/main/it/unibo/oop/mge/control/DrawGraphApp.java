package it.unibo.oop.mge.control;

import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.mge.c3d.Mesh;
import it.unibo.oop.mge.c3d.MeshDrawerBuilder;
import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.model.DrawGraph;
import it.unibo.oop.mge.model.DrawGraphImpl;
import it.unibo.oop.mge.model.FunctionFeaturesBuilderImpl;
import it.unibo.oop.mge.model.FunctionFeaturesImpl;
import it.unibo.oop.mge.model.FunctionParser;
import it.unibo.oop.mge.optionalColor.OptionalColor;
import it.unibo.oop.mge.optionalColor.OptionalColorBuilder;
import it.unibo.oop.mge.optionalColor.OptionalColorBuilderImpl;
import it.unibo.oop.mge.view.DrawGraphView;
import it.unibo.oop.mge.view.DrawGraphViewImpl;

public class DrawGraphApp implements DrawGraphViewObserver {

    private final DrawGraph model;
    private final DrawGraphView view;
    private Point3D visualizerTranslation = Point3D.origin();
    private double visualizerRotationXY;
    private double visualizerRotationYZ;
    private final List<Mesh> visualizerMeshes;

    public DrawGraphApp() {
        this.visualizerMeshes = new ArrayList<>();
        this.model = new DrawGraphImpl();
        this.view = new DrawGraphViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public final void newGraph(final String function, final double max, final double min, final double rate) {
        var c = new OptionalColorBuilderImpl().setBlue(103).setGreen(99).build();
        FunctionFeaturesImpl ff = new FunctionFeaturesBuilderImpl().setFunction(FunctionParser.parse(function))
                .setIntervals(new Pair<Double, Double>(min, max)).setRate(rate).setDinamicColor(c)
                .setDecimalPrecision(5).build();
        this.visualizerMeshes.add(Mesh.fromSegments(ff.getPolygonalModel()));
        this.visualizerMeshes.add(Mesh.fromSegments(ff.getPoligonalAxis()));
        this.view.plotGraph(MeshDrawerBuilder.create().addAll(visualizerMeshes).translation(visualizerTranslation)
                .rotationXY(visualizerRotationXY).rotationYZ(visualizerRotationYZ).build().render().getSegments());
    }

    public static void main(final String[] args) {
        new DrawGraphApp();
    }

    private void refreshVisualizer() {
        this.view.updateGraph(MeshDrawerBuilder.create().addAll(visualizerMeshes).translation(visualizerTranslation)
                .rotationXY(visualizerRotationXY).rotationYZ(visualizerRotationYZ).build().render().getSegments());
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub

    }

    @Override
    public void save() {
        // TODO Auto-generated method stub

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
        this.visualizerRotationXY += .26;
        this.refreshVisualizer();

    }

    @Override
    public final void decreaseXY() {
        this.visualizerRotationXY -= .26;
        this.refreshVisualizer();

    }

    @Override
    public final void increaseYZ() {
        this.visualizerRotationYZ += .26;
        this.refreshVisualizer();

    }

    @Override
    public final void decreaseYZ() {
        this.visualizerRotationYZ -= .26;
        this.refreshVisualizer();

    }

}
