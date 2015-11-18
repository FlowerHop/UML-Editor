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

import BasicObjects.UMLObject;
import Modes.Mode;




public class CanvasArea extends JPanel {
	private final double PORTS_SIDE = 6;
	
    private CanvasArea _canvas;
    
	private Vector _umlObjects = new Vector ();
	private Vector _umlLines = new Vector ();
	
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
    
    public Vector getContainedUMLObjects (int x, int y) {
      Vector result = new Vector ();
      Enumeration objects = _umlObjects.elements ();
        
      while (objects.hasMoreElements ()) {
        UMLObject each = (UMLObject) objects.nextElement ();
    	if (each.contains (x, y)) {
    	  result.add(each);
      	}
      }
       
      return result;
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
      
      Enumeration lines = _umlLines.elements ();
      
      while (lines.hasMoreElements()) {
    	Line2D line = (Line2D) lines.nextElement();
    	g2D.draw(line);
      }
      
      if (_mode != null && _mode.getConnectionPorts () != null) {
    	drawHighlightSquares (g2D, _mode.getConnectionPorts ());
      }
      
      if (_curCursor != null)
        setCursor (_curCursor);
    }
    
    public void drawObject (UMLObject obj) {
      if (obj != null) {
    	_umlObjects.add (obj);
      }
    }
    
    public void drawLine (Line2D line) {
      if (line != null) {
    	_umlLines.add(line);
      }
    }
    
    private void drawHighlightSquares (Graphics2D g2D, Point[] points) {
      g2D.setColor (Color.black);
      for (int i = 0; i < 4; i++) {
    	Point point = points[i];
    	g2D.fill(new Rectangle.Double (point.getX() - PORTS_SIDE/2, point.getY() - PORTS_SIDE/2, PORTS_SIDE, PORTS_SIDE));
      }
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