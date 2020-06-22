package it.unibo.oop.mge.c3d;

import it.unibo.oop.mge.c3d.geometry.Line;
import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;
/**
 * 
 * Base implementation of Point3DPerspective.
 *
 */
public class Point3DPerspectiveImpl implements Point3DPerspective {

    private static final Point2D FOCAL_POINT = Point2D.origin();

    private final Point3D point;

    Point3DPerspectiveImpl(final Point3D point) {
        this.point = point;
    }

    @Override
    public final Point2D render(final Point2D pointOfView) {
        final double finalX = Line.fromPoints(Point2D.fromDoubles(this.point.getX(), this.point.getY()), pointOfView)
                .getZero();
        double finalY;
        if (this.point.getX() == 0) {
            final var mockPoint = Point3D.fromDoubles(1, this.point.getY(), this.point.getZ());
            finalY = Point3DPerspective.fromPoint(mockPoint).render(pointOfView).getY();
        } else {
            final Line heightLine = Line.fromPoints(Point2D.fromDoubles(this.point.getX(), this.point.getZ()),
                    FOCAL_POINT);
            finalY = heightLine.solveFor(finalX);
        }
        return Point2D.fromDoubles(finalX, finalY);
    }

}
