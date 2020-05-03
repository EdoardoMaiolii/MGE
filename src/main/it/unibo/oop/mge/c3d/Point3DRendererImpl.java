package it.unibo.oop.mge.c3d;

import it.unibo.oop.mge.c3d.geometry.Line;
import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;

public class Point3DRendererImpl implements Point3DRenderer {

    private final Point3D point;

    private static final Point2D defaultPointOfView = Point2D.fromDoubles(0, -600);
    private static final Point2D focalPoint = Point2D.origin();

    @Override
    public final Point2D render(final Point2D pointOfView) {
        final double finalX = Line
                .fromPoints(Point2D.fromDoubles(this.point.getX(), this.point.getY()), defaultPointOfView).getZero();

        final Line heightLine = Line.fromPoints(Point2D.fromDoubles(this.point.getX(), this.point.getZ()), focalPoint);
        final double finalY = heightLine.solveFor(finalX);

        return Point2D.fromDoubles(finalX, finalY);
    }

    Point3DRendererImpl(final Point3D point) {
        this.point = point;
    }

}
