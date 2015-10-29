import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Demo extends JFrame {

  public Demo() {
    super();

    this.getContentPane().add(new CanvasArea());

    
    // it doesn't matter
//    JPanel panel = new JPanel();
//    panel.setLayout(new GridLayout(1, 2));
//    panel.add(new JLabel("x,y: ", JLabel.RIGHT));
//    location = new JLabel("");
//    panel.add(location);
//
//    container.add(panel, BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    setSize(600,300);
    setVisible(true);
  }

    public static void main(String arg[]) {
    new Demo();
  }

  
}