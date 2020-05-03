package c3d.geometry;

public class LineImpl implements Line {
    double m;
    double q;
    double zero;

    @Override
    public double getM() {
        return m;
    }

    @Override
    public double getQ() {
        return q;
    }

    @Override
    public double getZero() {
        return zero;
    }

    // package protected
    LineImpl(final Point2D a, final Point2D b) {

        if (a.getX() == b.getX()) {
            this.m = Double.POSITIVE_INFINITY;
            this.q = 0;
            this.zero = a.getX();
        } else {
            this.m = (a.getY() - b.getY()) / (a.getX() - b.getX());
            this.q = a.getY() - this.m * a.getX();
            if (m == 0) {
                this.zero = Double.NaN;
            } else {
                this.zero = -this.q / this.m;
            }
        }
    }

    @Override
    public double solveFor(final double x) {
        return this.m * x + this.q;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(m);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(q);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(zero);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        final LineImpl other = (LineImpl) obj;
        if (Double.doubleToLongBits(m) != Double.doubleToLongBits(other.m))
            return false;
        if (Double.doubleToLongBits(q) != Double.doubleToLongBits(other.q))
            return false;
        if (Double.doubleToLongBits(zero) != Double.doubleToLongBits(other.zero))
            return false;
        return true;
    }

}
