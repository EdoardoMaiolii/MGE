package c3d;

import c3d.geometry.Point2D;
import c3d.geometry.Point3D;

public interface Point3DRenderer {

    Point2D render(Point2D pointOfView);

    static Point3DRenderer fromPoint(Point3D point) {
        return new Point3DRendererImpl(point);
    }
}