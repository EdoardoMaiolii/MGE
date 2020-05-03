package c3d.geometry;

import java.awt.Color;
import java.util.function.Function;

public class Segment3DImpl implements Segment3D {
    @Override
    public String toString() {
        return "Segment3DImpl [a=" + a + ", b=" + b + ", color=" + color + "]";
    }

    private final Point3D a;
    private final Point3D b;
    private final Color color;

    @Override
    public Point3D getA() {
        return a;
    }

    @Override
    public Point3D getB() {
        return b;
    }

    // package proteted
    Segment3DImpl(final Point3D a, final Point3D b, final Color color) {
        this.a = a;
        this.b = b;
        this.color = color;
    }

    Segment3DImpl(final Point3D a, final Point3D b) {
        this(a, b, Color.black);
    }

    @Override
    public Segment3D translated(final double x, final double y, final double z) {
        return new Segment3DImpl(this.getA().translated(x, y, z), this.getB().translated(x, y, z), this.getColor());
    }

    @Override
    public Segment3D rotated(final double angleXY, final double angleYZ) {
        return new Segment3DImpl(this.getA().rotated(angleXY, angleYZ), this.getB().rotated(angleXY, angleYZ),
                this.getColor());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Segment3D transformed(final Function<Double, Double> transformation) {
        return new Segment3DImpl(this.a.transformed(transformation), this.b.transformed(transformation),
                this.getColor());
    }

    @Override
    public Segment3D translated(final Point3D vector) {
        return new Segment3DImpl(this.getA().translated(vector), this.getB().translated(vector), this.getColor());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((a == null) ? 0 : a.hashCode());
        result = prime * result + ((b == null) ? 0 : b.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Segment3DImpl other = (Segment3DImpl) obj;
        if (a == null) {
            if (other.a != null)
                return false;
        } else if (!a.equals(other.a))
            return false;
        if (b == null) {
            if (other.b != null)
                return false;
        } else if (!b.equals(other.b))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        return true;
    }
}
