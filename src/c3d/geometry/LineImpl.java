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

	//package protected
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

}
