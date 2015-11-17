import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class CanvasArea extends JPanel {
	private final double PORTS_SIDE = 6;
	
    private CanvasArea _canvas;
    
	private Vector _umlObjects = new Vector ();
	
	private Mode _mode;
    
    private Cursor _curCursor;
    
    public CanvasArea () {
      _canvas = this;
      setBackground (Color.white);
      MyMouseListener listener = new MyMouseListener ();
      addMouseListener (listener);
      addMouseMotionListener (listener);
    }
    
    public void changeMode (Mode mode) {
      _mode = mode;
      // Clean the high light squares for SelectMode 
      repaint ();
    }
    
    public UMLObject getContainedUMLObject (int x, int y) {
      Enumeration objects = _umlObjects.elements ();
        
      while (objects.hasMoreElements ()) {
        UMLObject each = (UMLObject) objects.nextElement ();
    	if (each.contains (x, y)) {
      	  return each;
      	}
      }
       
      return null;
    }
    
    public void paint (Graphics g) {
      super.paint (g);
      
      Graphics2D g2D = (Graphics2D) g;
      g2D.clearRect (0, 0, (int) getBounds ().getWidth (),(int) getBounds ().getHeight ());
      
      Enumeration objects = _umlObjects.elements ();
      
      while (objects.hasMoreElements ()) {
    	UMLObject obj = (UMLObject) objects.nextElement ();
    	obj.paintObject (g2D);
      }
      
      if (_mode != null && _mode.getBounding () != null) {
    	drawHighlightSquares (g2D, _mode.getBounding ());
      }
      
      if (_curCursor != null)
        setCursor (_curCursor);
    }
    
    public void drawObject (UMLObject obj) {
      if (obj != null) {
    	_umlObjects.add (obj);
      }
    }
  
    public UMLObject moveUMLObject (UMLObject obj, double toX, double toY, double width, double height) {
      obj.moveTo ((int) toX, (int) toY);
      return obj;
    }
    
    private void drawHighlightSquares (Graphics2D g2D, Rectangle2D r) {
      double x = r.getX ();
      double y = r.getY ();
      double w = r.getWidth ();
      double h = r.getHeight ();
      g2D.setColor (Color.black);
      g2D.fill (new Rectangle.Double (x + w * 0.5 - PORTS_SIDE/2, y - PORTS_SIDE/2, PORTS_SIDE, PORTS_SIDE));
      g2D.fill (new Rectangle.Double (x - PORTS_SIDE/2, y + h * 0.5 - PORTS_SIDE/2, PORTS_SIDE, PORTS_SIDE));
      g2D.fill (new Rectangle.Double (x + w - PORTS_SIDE/2, y + h * 0.5 - PORTS_SIDE/2, PORTS_SIDE, PORTS_SIDE));
      g2D.fill (new Rectangle.Double (x + w * 0.5 - PORTS_SIDE/2, y + h - PORTS_SIDE/2, PORTS_SIDE, PORTS_SIDE));
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