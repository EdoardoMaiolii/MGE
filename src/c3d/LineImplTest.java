package c3d;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import c3d.geometry.Line;
import c3d.geometry.LineImpl;
import c3d.geometry.Point2D;
import c3d.geometry.Point2DImpl;

class LineImplTest {

	@Test
	void test() {
		Line a = Line.fromPoints(Point2D.fromDoubles(-10, -10), Point2D.fromDoubles(10, 10));
		assertEquals(10, a.solveFor(10));
	}

}
