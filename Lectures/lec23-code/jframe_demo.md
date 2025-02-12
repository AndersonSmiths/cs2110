# JShell demo illustrating JFrame operations

```java
import java.awt.*;
import javax.swing.*;
JFrame frame = new JFrame();
frame.setVisible(true);
frame.setTitle("My Window");
// resize
frame.getSize(); // Dimension
// close - just hidden
frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
// close - nothing happens
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setPreferredSize(new Dimension(480, 360));
frame.pack();
// Normally setVisible comes last

frame.add(new JLabel("Hello"), BorderLayout.NORTH)
// Nothing happens until setVisible(true) again
```