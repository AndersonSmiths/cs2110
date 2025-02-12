import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class ClickerDriver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Clicker demo");
            // create and add clicker widget
            Clicker clicker = new Clicker();
            frame.add(clicker);

            // create and add a reset button to the bottom of the frame
            JButton reset = new JButton("Reset");
            frame.add(reset, BorderLayout.SOUTH);
            // when reset button is pressed, reset the clicker
            reset.addActionListener((ActionEvent e) -> {clicker.reset();});

            // create and add a menu bar with save and exit options
            JMenuItem saveItem = new JMenuItem("Save coordinates");
            JMenuItem exitItem = new JMenuItem("Exit");
            // TODO 3: Add a menu bar with a "File" menu to the frame. The
            // menu items `saveItem` and `exitItem` should be accessible under the
            // "File" menu. See the Menu tutorial [1] for example code you can adapt.
            // You do not need to add the mnemonics, keyboard shortcuts, or hover over
            // descriptions shown in that tutorial.
            // [1] https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html

            // When "Exit" menu item is activated, dispose of the JFrame.
            exitItem.addActionListener((ActionEvent ae) -> frame.dispose());

            // TODO 4: When "Save Coordinates" menu item is activated, save coordinates to file
            // (hint: call helper method ClickerDriver.saveCoordinates())

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        });
    }

    /**
     * Append a line containing `score` to a user-selected file, using `frame` as the parent of any
     * dialogs. Show an error dialog if a problem occurs when writing the file.
     */
    private static void saveCoordinates(JFrame frame, Clicker clicker) {
        // TODO 5:
        // * Show a "save file" dialog [1].
        // * If the user selects a file, write the value in `score` on a new
        // line of text at the end of the file, retaining its former contents
        // (see handout).
        // * If a problem occurs when opening or writing to the file, show an
        // error dialog with the class of the exception as its title and the
        // class of the exception and exception's message as its text [2].
        // [1]
        // https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        // [2] https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html

    }
}
