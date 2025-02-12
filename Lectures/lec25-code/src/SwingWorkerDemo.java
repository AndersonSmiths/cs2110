import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class SwingWorkerDemo {
    public static void main(String[] args) {
        System.out.println("Running main on thread " + Thread.currentThread());
        SwingUtilities.invokeLater(() -> {
            System.out.println("Setting up GUI on thread " + Thread.currentThread());

            JFrame frame = new JFrame("SwingWorker demo");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label = new JLabel("Ready to compute the meaning of life?");
            frame.add(label, BorderLayout.NORTH);
            frame.add(new JLabel("Progress"), BorderLayout.WEST);
            JProgressBar progressBar = new JProgressBar(0, 100);
            frame.add(progressBar);

            ExpensiveTask task = new ExpensiveTask(label);
            // Listen for progress updates from `task` and move progress bar when received
            task.addPropertyChangeListener(e -> {
                if (e.getPropertyName().equals("progress")) {
                    progressBar.setValue((Integer) e.getNewValue());
                }
            });

            JButton button = new JButton("Go!");
            frame.add(button, BorderLayout.SOUTH);
            button.addActionListener(event -> {
                System.out.println("Handling button press on thread " + Thread.currentThread());
                task.execute();
            });

            frame.add(progressBar);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class ExpensiveTask extends SwingWorker<String, Object> {
    /**
     * Label to display the answer when done.
     */
    JLabel label;

    public ExpensiveTask(JLabel label) {
        this.label = label;
    }

    @Override
    public String doInBackground() throws InterruptedException {
        // Will be called on worker thread
        System.out.println("Contemplating life on thread " + Thread.currentThread());
        for (int i = 0; i < 10; ++i) {
            // Pause for 1/2 s to simulate doing expensive work
            Thread.sleep(500);

            // Emit a progress (property) update each iteration
            setProgress((i + 1) * 10);
        }
        return "The meaning of life is 42";
    }

    @Override
    public void done() {
        // Will be called on EDT
        System.out.println("Handling result on thread " + Thread.currentThread());
        try {
            String result = get();
            label.setText(result);
        } catch (Exception e) {
            // FIXME: This is only appropriate for demo purposes
            e.printStackTrace();
        }
    }
}
