package cs2110;

/**
 * A location in a 2D plane's coordinate system.  Immutable.
 */
public class Point {

    /**
     * Horizontal displacement from the y-axis.  Finite.
     */
    private final double x;

    /**
     * Vertical displacement from the x-axis.  Finite.
     */
    private final double y;

    /**
     * Construct a point at the origin of the coordinate system.
     */
    public Point() {
        // Final fields have no default value, so must assign explicitly.
        x = 0.0;
        y = 0.0;
    }

    /**
     * Construct a point with given Cartesian coordinates.
     *
     * @param x Point's horizontal displacement from the y-axis.  Finite.
     * @param y Point's vertical displacement from the x-axis.  Finite.
     */
    public Point(double x, double y) {
        assert Double.isFinite(x);
        assert Double.isFinite(y);
        this.x = x;
        this.y = y;
    }

    /**
     * The point's horizontal displacement from the y-axis.  Finite.
     */
    public double x() {
        return x;
    }

    /**
     * The point's vertical displacement from the x-axis.  Finite.
     */
    public double y() {
        return y;
    }

    /**
     * Return a point displaced from this point by the provided amounts.
     *
     * @param dx Horizontal displacement of result relative to this point.  Finite.
     * @param dy Vertical displacement of result relative to this point.  Finite.
     * @return Point at displaced location.
     */
    public Point shifted(double dx, double dy) {
        assert Double.isFinite(x);
        assert Double.isFinite(y);
        // Bug: Sum could overflow, violating constructor precondition.
        return new Point(x + dx, y + dy);
    }

    /**
     * A String representation of this point's coordinates, which are surrounded by parentheses and
     * separated by a comma.
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * True iff obj is a Point at the same location as this point.
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Point p = (Point) obj;
        return (x == p.x) && (y == p.y);
    }

    // Must override hashCode when overriding equals.
    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }
}
