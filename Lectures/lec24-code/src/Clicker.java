import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * A square widget showing a circle whose color depends on the number of times
 * it has been selected by a mouse click. It also keeps track of click coordinates.
 */
public class Clicker extends JPanel implements MouseListener {

    /** The x coordinate of the upper left corner of the circle. Must not be negative. */
    final int circleX = 20;

    /** The y coordinate of the upper left corner of the circle. Must not be negative. */
    final int circleY = 20;

    /** The number of times the clicker has been selected by a mouse click. Must not be negative. */
    private int count;

    /** The list of locations where the clicker has been clicked by the user. */
    private List<Coordinate> clickCoordinates;

    /** A color palette for the drawn circle. */
    // Feel free to customize with other colors!
    private Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN};

    /**
     * Creates a new clicker widget.
     */
    public Clicker() {
        count = 0;
        clickCoordinates = new ArrayList<Coordinate>();
        setPreferredSize(new Dimension(500, 500));
        addMouseListener(this);
    }

    /**
     * Resets the count and clickCoordinates, then triggers a repaint.
     */
    public void reset() {
        count = 0;
        clickCoordinates = new ArrayList<>();
        repaint();
    }

    /** Returns the number of times the widget has been clicked. */
    public int getCount() {
        return count;
    }

    /** Returns a list of places in the widget that were clicked, in order. */
    public List<Coordinate> getClickCoordinates() {
        return new ArrayList<>(clickCoordinates);
    }

    /**
     * Fills the background with light gray, and fills in a circle with a color from
     * `colors` based on the number of clicks made.
     * Also fills text describing the number of clicks made, and the coordinate
     * of the last click ((0, 0) if no clicks were made).
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Paint the default background. Keep this as the first line of the method.
        super.paintComponent(g);

        // Fills the background with a light gray color
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());

        // TODO 1: Draw a circle with a different color.
        // Use these classes and methods: Graphics.setColor, Graphics.fillOval [1]; Color [2]
        // You can use the colors field as a palette to cycle through to display different colors.
        // Also, use the circleX and circleY fields for the upper left coordinate.
        // [1]:
        // https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/Graphics.html
        // [2]: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/Color.html

        // Draws a string showing the last coordinate clicked.
        g.setColor(Color.BLACK);
        Coordinate lastCoordinate =
                clickCoordinates.isEmpty() ? new Coordinate() : clickCoordinates.getLast();
        g.drawString("Last Coordinate: " + lastCoordinate, 24, 24);

        // TODO 2: Draw a string representing the current count
        // Hint: Use Graphics.drawString() [1]
        // [1]:
        // https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/Graphics.html
    }

    /**
     * Handle a mouse button being pressed. Increments count by 1, notifies observers, and
     * requests a repaint (paintComponent() is called by Swing when a repaint happens).
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO (Challenge): Currently, the count increments and a repaint occurs
        //  regardless of whether the mouse click happened *within* the circle.
        //  Write a routine to increment the count and repaint *only* when the
        //  mouse click happens within the circle.
        Coordinate clickCoordinate = new Coordinate(e.getX(), e.getY());
        System.out.println("Mouse click at " + clickCoordinate);
        clickCoordinates.add(clickCoordinate);
        count++;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
