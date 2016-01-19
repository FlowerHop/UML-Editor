package UI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JPanel;

import Shape.Shape;
import Modes.Mode;
import Shape.BasicObject;
import Shape.LineObject;




public class CanvasArea extends JPanel {
	private final double PORTS_SIDE = 6;
	
	private Vector _umlShapes = new Vector ();
	
	private Mode _mode = null;
    
    private Cursor _curCursor;
    
    private int currentDepth = 99;
    
    private static CanvasArea uniqueInstance;
    
    private CanvasArea () {
      setBackground (Color.white);
      MyMouseListener listener = new MyMouseListener ();
      addMouseListener (listener);
      addMouseMotionListener (listener);
    }
    
    public static CanvasArea getInstance () {
      if (uniqueInstance == null) {
    	uniqueInstance = new CanvasArea ();
      }
      
      return uniqueInstance;
    }
    
    public void setMode (Mode mode) {
      _mode = mode;
      // Clean the high light squares for SelectMode 
      repaint ();
    }
    
    public Mode getCurrentMode () {
      return _mode;
    }
    
      
   
    
    public void paint (Graphics g) {
      super.paint (g);
      
      Graphics2D g2D = (Graphics2D) g;
      g2D.clearRect (0, 0, (int) getBounds ().getWidth (),(int) getBounds ().getHeight ());
      
      Enumeration objects = _umlShapes.elements ();
      
      while (objects.hasMoreElements ()) {
    	Shape obj = (Shape) objects.nextElement ();
    	obj.paintObject (g2D);
      }
      
      if (_curCursor != null)
        setCursor (_curCursor);
    }
    
    public void drawShape (Shape shape) {
      shape.setDepth (currentDepth);
      _umlShapes.add (shape);
      currentDepth--;
    }
    
    public void removeShape (Shape shape) {
      _umlShapes.remove (shape);
    }
    
    public Vector getShapes () {
      return _umlShapes;
    }
    
    class MyMouseListener extends MouseAdapter {
        public void mouseMoved (MouseEvent e) {
          if (_mode != null)
            _mode.onMoved (e); 
        }
       
        public void mousePressed (MouseEvent e) {
          if (_mode != null)
            _mode.onPressed (e);
        }
        
        public void mouseDragged (MouseEvent e) {
          if (_mode != null)
            _mode.onDragged (e);
        }
        
        public void mouseReleased (MouseEvent e) {
          if (_mode != null)
            _mode.onReleased (e);
        }

        public void mouseClicked (MouseEvent e) {
    	 
        }
    }    
}