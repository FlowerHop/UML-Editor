package UI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Demo extends JFrame {
    public Demo () {
      super ();

      addWindowListener (new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
          System.exit (0);
        }
      });
      this.setContentPane (new MainPanel ());
      this.setSize (1000, 600);
      setVisible (true);
    }

    public static void main (String arg[]) {
      new Demo ();
    } 
}