package events;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Display a photograph taken during the season corresponding to the last button that was clicked.
 * Current directory must contain "winter.jpg", "spring.jpg", "summer.jpg", and "fall.jpg".
 */
public class AvaSwing extends JFrame {

    public AvaSwing() {
        setTitle("AvaSwing demo");

        // Allow application to exit when window is closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add label for image to center
        JLabel pic= new JLabel(new ImageIcon("spring.jpg"));
        add(pic);

        // Add buttons around border whose actions change the image
        JButton winter= new JButton("Winter");
        add(winter, BorderLayout.NORTH);

        JButton spring= new JButton("Spring");
        add(spring, BorderLayout.EAST);

        JButton summer= new JButton("Summer");
        add(summer, BorderLayout.SOUTH);

        JButton fall= new JButton("Fall");
        add(fall, BorderLayout.WEST);

        // Compute component sizes and set window size
        pack();

        // Register listeners to change picture when buttons are clicked
        winter.addActionListener(e -> pic.setIcon(new ImageIcon("winter.jpg")));
        spring.addActionListener(e -> pic.setIcon(new ImageIcon("spring.jpg")));
        summer.addActionListener(e -> pic.setIcon(new ImageIcon("summer.jpg")));
        fall.addActionListener(e -> pic.setIcon(new ImageIcon("fall.jpg")));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AvaSwing app= new AvaSwing();
            app.setVisible(true);
        });
    }
}
