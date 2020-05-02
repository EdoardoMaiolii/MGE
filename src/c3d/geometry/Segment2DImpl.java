package c3d.geometry;

import java.awt.Color;
import java.util.function.Function;

public class Segment2DImpl implements Segment2D {
    private final Point2D a;
    private final Point2D b;
    private final Color color;

    @Override
    public Point2D getA() {
        return a;
    }

    @Override
    public String toString() {
        return "Segment2DImpl [a=" + a + ", b=" + b + "]";
    }

    @Override
    public Point2D getB() {
        return b;
    }

    // package protected
    Segment2DImpl(final Point2D a, final Point2D b, final Color color) {
        super();
        this.a = a;
        this.b = b;
        this.color = color;
    }

    Segment2DImpl(final Point2D a, final Point2D b) {
        this(a, b, Color.black);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Segment2D transformed(final Function<Double, Double> transformation) {
        return new Segment2DImpl(this.a.transformed(transformation), this.b.transformed(transformation),
                this.getColor());
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
        final Segment2DImpl other = (Segment2DImpl) obj;
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
