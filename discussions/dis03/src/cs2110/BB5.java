package cs2110;

/**
 * A BoundingBox represented by the locations of its lower-left and upper-right corners.
 */
public class BB5 implements BoundingBox {

    /**
     * Location of the lower-left corner of this box (point with minimum x-coordinate and minimum
     * y-coordinate).  Non-null.
     */
    private final Point lower;

    /**
     * Location of the upper-right corner of this box (point with maximum x-coordinate and maximum
     * y-coordinate).  Non-null.  Invariant: {@code upper.x >= lower.x AND upper.y >= lower.y}.
     */
    private final Point upper;

    /**
     * Determine whether the class invariant is satisfied.
     *
     * @return True iff the class invariant is satisfied.
     */
    private boolean checkInvariant() {
        return (lower.x() <= upper.x()) &&
                (lower.y() <= upper.y());
    }

    /**
     * Construct a new BoundingBox with the specified lower-left and upper-right corner locations.
     *
     * @param lower Location of the lower-left corner.  Non-null.
     * @param upper Location of the upper-right corner.  Non-null, and {@code upper.x >= lower.x}
     *         and {@code upper.y >= lower.y}.
     */
    public BB5(Point lower, Point upper) {
        assert lower.x() <= upper.x();
        assert lower.y() <= upper.y();

        this.lower = lower;
        this.upper = upper;

        assert checkInvariant();
    }

    @Override
    public double width() {
        // Because all fields are final and immutable and invariants are checked at the end of all
        // constructors, these checks are redundant.  We've written them anyway to illustrate good
        // habits.
        assert checkInvariant();

        // Box width is equal to the horizontal (x) distance between the lower and upper points.
        return upper.x() - lower.x();
    }

    @Override
    public double height() {
        assert checkInvariant();

        // Box height is equal to the vertical (y) distance between the lower and upper points.
        return upper.y() - lower.y();
    }

    @Override
    public Point centroid() {
        assert checkInvariant();

        // The centroid is the average of the lower and upper points.
        return new Point(0.5 * (upper.x() + lower.x()),
                0.5 * (upper.y() + lower.y()));
    }

    @Override
    public Point[] corners() {
        assert checkInvariant();

        // Allocate return value (arrays are mutable, so memoization is not an option).
        Point[] ans = new Point[4];

        // Lower-left
        ans[0] = lower;
        // Lower-right
        ans[1] = lower.shifted(width(), 0);
        // Upper-right
        ans[2] = upper;
        // Upper-left
        ans[3] = lower.shifted(0, height());

        return ans;
    }

    @Override
    public double area() {
        assert checkInvariant();

        // The area of a rectangle is the product of its width and height.
        return width() * height();
    }

    @Override
    public boolean contains(Point p) {
        assert checkInvariant();

        // Check if `p`'s coordinates are strictly between our lower and upper coordinates.
        return (p.x() > lower.x()) || (p.x() < upper.x()) ||
                (p.y() > lower.y()) || (p.y() < upper.y());
    }

    @Override
    public boolean contains(BoundingBox b) {
        assert checkInvariant();

        // `b` is contained iff all of its corners are contained.
        Point[] corners = b.corners();
        for (int i = 0; i < corners.length; ++i) {
            if (!contains(corners[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public BoundingBox intersect(BoundingBox b) {
        assert checkInvariant();

        // Determine lower and upper points of `b`.  Must extract from `corners` (wasteful) because
        // `b` may not be a TwoPointBoundingBox.
        Point[] bCorners = b.corners();
        Point bLower = bCorners[0];
        Point bUpper = bCorners[2];

        // Compute smallest upper and largest lower coordinate in both dimensions to get
        // intersection bounds.
        double xMax = Math.min(upper.x(), bUpper.x());
        double xMin = Math.max(lower.x(), bLower.x());
        double yMax = Math.min(upper.y(), bUpper.y());
        double yMin = Math.max(lower.y(), bLower.y());

        // Check if intersection bounds are inverted.
        if ((xMin > xMax) || (yMin > yMax)) {
            // Intersection is empty set; return zero-area box.
            return new BB5(new Point(), new Point());
        }
        return new BB5(new Point(xMin, yMin), new Point(xMax, yMax));
    }

    @Override
    public BoundingBox scale(double s) {
        assert s >= 0.0 && Double.isFinite(s);
        assert checkInvariant();

        // Compute difference between new and old dimensions.
        double dw = (s - 1.0) * width();
        double dh = (s - 1.0) * height();

        // Shift corners by half the change in dimensions (in opposite directions).
        return new BB5(lower.shifted(-0.5 * dw, -0.5 * dh),
                upper.shifted(0.5 * dw, 0.5 * dh));
    }

    @Override
    public BoundingBox shifted(double dx, double dy) {
        assert Double.isFinite(dx);
        assert Double.isFinite(dy);
        assert checkInvariant();

        // Shift both corners by the specified displacement.
        return new BB5(lower.shifted(dx, dy),
                upper.shifted(dx, dy));
    }


    @Override
    public String toString() {
        return "TwoPointBoundingBox(" + lower + " -> " + upper + ")";
    }

    @Override
    public boolean equals(Object obj) {
        assert checkInvariant();
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        BB5 b = (BB5) obj;
        return lower.equals(b.lower) && upper.equals(b.upper);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(lower, upper);
    }
}
