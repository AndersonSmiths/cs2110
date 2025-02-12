public class Coordinate {

    /** The x value of the coordinate. Must not be negative. */
    private int x;

    /** The y value of the coordinate. Must not be negative. */
    private int y;

    /** Creates a coordinate at (0, 0). */
    public Coordinate() {
        this(0, 0);
    }

    /** Creates a coordinate at (x, y). */
    public Coordinate(int x, int y) {
        assert x >= 0;
        assert y >= 0;
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
