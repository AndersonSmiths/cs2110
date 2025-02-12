package cs2110;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundingBoxTest {

    static BoundingBox makeBoundingBox(Point lower, Point upper) {
        //return new BB1(lower, upper);
        //return new BB2(lower, upper);
        //return new BB3(lower, upper);
        //return new BB4(lower, upper);
        //return new BB5(lower, upper);
        //return new BB6(lower, upper);
    }

    @DisplayName("WHEN a point is at the center of a non-empty bounding box, "
            + "THEN it should be contained in the bounding box.")
    @Test
    void testContains1() {
        BoundingBox bb = makeBoundingBox(new Point(0, 0), new Point(1, 1));
        Point p = new Point(0.5, 0.5);
        assertTrue(bb.contains(p));
    }

    @DisplayName("WHEN a point is outside the non-empty bounding box, " +
            "THEN it should not be contained in the bounding box")
    @Test
    void testContains2() {
        BoundingBox bb = makeBoundingBox(new Point(0, 0), new Point(1, 1));
        Point p = new Point(2, 2);
        assertFalse(bb.contains(p));
    }

    @DisplayName("WHEN a point is on the boundary of a non-empty bounding box, " +
            "THEN it should not be contained in the bounding box.")
    @Test
    void testContains3() {
        BoundingBox bb = makeBoundingBox(new Point(0, 0), new Point(1, 1));
        Point p = new Point(0, 0);
        assertFalse(bb.contains(p));
    }

    @DisplayName("WHEN a point is on the boundary of an empty bounding box, " +
            "THEN it should not be contained in the bounding box.")
    @Test
    void testContains4() {
        BoundingBox bb = makeBoundingBox(new Point(0, 0), new Point(0, 0));
        Point p = new Point(0, 0);
        assertFalse(bb.contains(p));
    }
}
