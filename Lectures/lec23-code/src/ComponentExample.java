package cs2110;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Demonstrate placement of components in a JFrame. Use BorderLayout. It places five components in
 * the five possible areas: (1) a JButton in the east, (2) a JLabel in the west, (3) a JCheckBox in
 * the south, (4) a JTextField in the north, and (5) a JTextArea in the center.
 */
public class ComponentExample extends JFrame {
    /**
     * Constructor: a JFrame with 5 components
     */
    public ComponentExample() {
        super("ComponentExample");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set up child components
        JButton button = new JButton("click me");
        JTextField textField = new JTextField("type here", 22);
        JCheckBox checkBox = new JCheckBox("I am vaccinated against COVID-19");
        JLabel label = new JLabel("A label");
        // Make the text area just big enough; any new rows that are typed will not be visible.
        JTextArea textArea = new JTextArea("Four\nscore and seven\nyears\nago", 4, 10);
        // wrap the scroll pane around textArea
//        JScrollPane scrollPane = new JScrollPane(textArea);

        // Use a big font for better visibility when projecting
        Font bigFont = new Font("Arial", Font.PLAIN, 22);
        button.setFont(bigFont);
        textField.setFont(bigFont);
        checkBox.setFont(bigFont);
        label.setFont(bigFont);
        textArea.setFont(bigFont);

        // Add components to our layout
        add(button, BorderLayout.EAST);
        add(textField, BorderLayout.NORTH);
        add(checkBox, BorderLayout.SOUTH);
        add(label, BorderLayout.WEST);
//        add(textArea, BorderLayout.CENTER);  // textArea was added to scrollPane above
//        add(scrollPane, BorderLayout.CENTER); // replace above with this code

        // Resize to accommodate components' preferred sizes
        pack();
    }

    /**
     * Show the GUI
     */
    public static void main(String[] pars) {
        // Lecture 24 will explain this incantation.
        // Basically, Swing components should only be access from Swing's own thread.
        SwingUtilities.invokeLater(() -> new ComponentExample().setVisible(true));
    }
}