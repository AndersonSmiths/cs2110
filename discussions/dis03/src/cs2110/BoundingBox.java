package cs2110;

/**
 * An axis-aligned rectangle in a 2D plane.
 */
public interface BoundingBox {

    /**
     * The width of the box (in units of the coordinate system).  Finite and non-negative.
     */
    double width();

    /**
     * The height of the box (in units of the coordinate system).  Finite and non-negative.
     */
    double height();

    /**
     * The location of the centroid (center of area) of the box in the coordinate system. Non-null.
     */
    Point centroid();

    /**
     * The locations of the corners of the box in the coordinate system.  Corners are enumerated
     * counter-clockwise, starting with the lower-left corner (minimum x &amp; y coordinates).
     *
     * @return Length-4 array containing the (non-null) locations of the corners.
     */
    Point[] corners();

    /**
     * The area enclosed by the box (in squared units of the coordinate system).  Non-negative and
     * non-NaN.
     */
    double area();

    /**
     * Determine whether the specified point is contained strictly within the interior of this box.
     * Points on the boundary of the box are not considered to be contained within its interior.
     *
     * @param p The point to perform the interior test on.  Non-null.
     * @return True if <code>p</code> is strictly within the interior of this box; otherwise false.
     */
    boolean contains(Point p);

    /**
     * Determine whether the interior+boundary of a specified box is a subset of the interior of
     * this box.
     *
     * @param b The box to perform the interior test on.  Non-null.
     * @return True if the closure of <code>b</code> is a subset of the interior of this box;
     * otherwise false.
     */
    boolean contains(BoundingBox b);

    /**
     * Compute the intersection of this box with another box.  If the two boxes do not intersect,
     * return a BoundingBox with zero area.
     *
     * @param b The box to intersect this box with.  Non-null.
     * @return A (non-null) BoundingBox representing the area common to this and <code>b</code>.
     */
    // Note: This behavior is only sensible for "open" boxes; otherwise, it implies that
    // non-intersecting boxes actually intersect at a point.  It would be better to have a special
    // value for representing empty boxes.
    BoundingBox intersect(BoundingBox b);

    /**
     * Compute the result of scaling the linear dimensions of this box by the specified factor while
     * preserving the box's centroid.
     *
     * @param s Factor by which box's linear dimensions should be scaled.  Finite and non-negative.
     * @return A (non-null) BoundingBox representing the result of the scaling.
     */
    BoundingBox scale(double s);

    /**
     * Compute the result of shifting this box by a specified displacement.
     *
     * @param dx Coordinate distance to shift box by in x-direction.  Finite.
     * @param dy Coordinate distance to shift box by in y-direction.  Finite.
     * @return A (non-null) BoundingBox representing the result of the shift.
     */
    BoundingBox shifted(double dx, double dy);
}
