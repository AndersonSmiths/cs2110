import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JButton summer = new JButton("Summer");
        add(summer, BorderLayout.SOUTH );

        JButton spring = new JButton("Spring");
        add(spring, BorderLayout.EAST);

        JButton fall = new JButton("Fall");
        add(fall, BorderLayout.WEST);

        // registers basic action listener
        winter.addActionListener(new WinterBasicActionListener());
        spring.addActionListener(e-> {
            System.out.println("Spring was pressed.");
        });

        winter.addActionListener((ActionEvent e) -> {
            pic.setIcon(new ImageIcon("winter.jpg"));
        });

        spring.addActionListener((ActionEvent e) -> {
            pic.setIcon(new ImageIcon("spring.jpg"));
        });

        fall.addActionListener((ActionEvent e) -> {
            pic.setIcon(new ImageIcon("fall.jpg"));
        });

        summer.addActionListener((ActionEvent e) -> {
            pic.setIcon(new ImageIcon("summer.jpg"));
        });
        // Compute component sizes and set window size
        pack();


        // TODO 2: Register anonymous function listeners to
        //  change picture when buttons are clicked
        // Hint: Use the JLabel.setIcon(ImageIcon icon) method to set a new picture.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AvaSwing app= new AvaSwing();
            app.setVisible(true);
        });
    }

}

class WinterBasicActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Winter button was pressed at " + e.getWhen());
    }
}