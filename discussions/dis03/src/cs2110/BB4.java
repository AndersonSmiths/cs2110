package cs2110;

/**
 * A BoundingBox represented by the location of its centroid and its horizontal and vertical
 * extents.
 */
public class BB4 implements BoundingBox {

    /**
     * Location of box's centroid.  Non-null.
     */
    private final Point center;

    /**
     * Width of box (in coordinate system units).  Finite and non-negative.
     */
    private final double width;

    /**
     * Height of box (in coordinate system units).  Finite and non-negative.
     */
    private final double height;

    /**
     * Determine whether the class invariant is satisfied.
     *
     * @return True iff the class invariant is satisfied.
     */
    private boolean checkInvariant() {
        return center != null &&
                width >= 0.0 && Double.isFinite(width) &&
                height >= 0.0 && Double.isFinite(height);
    }

    public BB4(Point lower, Point upper) {
        this(new Point(0.5*(lower.x() + upper.x()), 0.5*(lower.y() + upper.y())),
                upper.x() - lower.x(), upper.y() - lower.y());
    }

    public BB4(Point center, double width, double height) {
        assert center != null;
        assert width >= 0.0 && Double.isFinite(width);
        assert height >= 0.0 && Double.isFinite(height);

        this.center = center;
        this.width = width;
        this.height = height;

        assert checkInvariant();
    }

    @Override
    public double width() {
        // Because all fields are final and immutable and invariants are checked at the end of all
        // constructors, these checks are redundant.  We've written them anyway to illustrate good
        // habits.
        assert checkInvariant();

        return width;
    }

    @Override
    public double height() {
        assert checkInvariant();

        return height;
    }

    @Override
    public Point centroid() {
        assert checkInvariant();

        return center;
    }

    @Override
    public Point[] corners() {
        assert checkInvariant();

        // Allocate return value (arrays are mutable, so memoization is not an option).
        Point[] ans = new Point[4];

        // Lower-left
        ans[0] = center.shifted(-width / 2, -height / 2);

        // Lower-right
        ans[1] = center.shifted(width / 2, -height / 2);

        // Upper-right
        ans[2] = center.shifted(width / 2, height / 2);

        // Upper-left
        ans[3] = center.shifted(-width / 2, height / 2);

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

        // Check whether point is within half-extents of center.
        return (p.x() - center.x() < width / 2) &&
                (p.y() - center.y() < height / 2);
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

        // Compute locations of our lower-left and upper-right corners.
        Point lower = center.shifted(-width / 2, -height / 2);
        Point upper = center.shifted(width / 2, height / 2);

        // Determine lower and upper points of `b`.  Must request all corners (wasteful) because we
        // don't know the representation of `b`.
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
            return new BB4(new Point(), 0.0, 0.0);
        }
        // Convert to center+extents representation.
        return new BB4(new Point(0.5 * (xMin + xMax), 0.5 * (yMin + yMax)),
                xMax - xMin, yMax - yMin);
    }

    @Override
    public BoundingBox scale(double s) {
        assert s >= 0.0 && Double.isFinite(s);
        assert checkInvariant();

        // Scale extents;
        return new BB4(center, s * width, s * height);
    }

    @Override
    public BoundingBox shifted(double dx, double dy) {
        assert Double.isFinite(dx);
        assert Double.isFinite(dy);
        assert checkInvariant();

        // Shift center.
        return new BB4(center.shifted(dx, dy), width, height);
    }

    @Override
    public String toString() {
        return "CenterExtentsBoundingBox(" + center + ", " + width + "x" + height + ")";
    }

    @Override
    public boolean equals(Object obj) {
        assert checkInvariant();
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        BB4 b = (BB4) obj;
        return center.equals(b.center) && (width == b.width) && (height == b.height);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(center, width, height);
    }
}
