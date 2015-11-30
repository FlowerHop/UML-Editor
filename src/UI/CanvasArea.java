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

import BasicObjects.Composable;
import BasicObjects.UMLObject;
import ConnectionLines.ConnectionLine;
import Modes.Mode;




public class CanvasArea extends JPanel {
	private final double PORTS_SIDE = 6;
    
	private Vector _umlLines = new Vector ();
	
	private Vector _umlComposables = new Vector ();
	
	private Mode _mode = null;
    
    private Cursor _curCursor;
    
    private int currentDepth = 99;
    
    public CanvasArea () {
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
    
    public Mode getCurrentMode () {
      return _mode;
    }
    
    public Vector getContainedComposables (int x, int y) {
      Vector result = new Vector ();
      Enumeration objects = _umlComposables.elements ();
          
      while (objects.hasMoreElements ()) {
        Composable each = (Composable) objects.nextElement ();
          
      	if (each.contains (x, y)) {
          result.add(each);
        }
      }
        
      return result;
    }
      
    public Vector getContainedComposables (Rectangle2D bounding) {
      Vector result = new Vector ();
      Enumeration objects = _umlComposables.elements ();
        
      while (objects.hasMoreElements ()) {
        Composable each = (Composable) objects.nextElement ();
      	if (each.contains (bounding)) {
      	  result.add(each);
        }
      }
         
      return result;
    }
    
    public Vector getContainedUMLObjects (int x, int y) {
      Vector result = new Vector ();
      Enumeration objects = _umlComposables.elements ();
            
      while (objects.hasMoreElements ()) {
        Composable each = (Composable) objects.nextElement ();
            
        if (each.contains (x, y) && each instanceof UMLObject) {
          result.add(each);
        }
      }
          
      return result;
    }
    
    public Vector getContainedUMLObjects (Rectangle2D bounding) {
      Vector result = new Vector ();
      Enumeration objects = _umlComposables.elements ();
          
      while (objects.hasMoreElements ()) {
        Composable each = (Composable) objects.nextElement ();
        if (each.contains (bounding) && each instanceof UMLObject) {
          result.add(each);
        }
      }
           
      return result;
    }
    
    public void paint (Graphics g) {
      super.paint (g);
      
      Graphics2D g2D = (Graphics2D) g;
      g2D.clearRect (0, 0, (int) getBounds ().getWidth (),(int) getBounds ().getHeight ());
      
      Enumeration objects = _umlComposables.elements ();
      
      while (objects.hasMoreElements ()) {
    	Composable obj = (Composable) objects.nextElement ();
    	obj.paintObject (g2D);
      }
      
      Enumeration lines = _umlLines.elements ();
      
      while (lines.hasMoreElements()) {
    	ConnectionLine line = (ConnectionLine) lines.nextElement ();
    	line.paintLine (g2D);
      }
      
      if (_curCursor != null)
        setCursor (_curCursor);
    }
    
    public void drawComposable (Composable composable) {
      composable.setDepth (currentDepth);
      _umlComposables.add (composable);
      currentDepth--;
    }
    
    public void removeComposable (Composable composable) {
      _umlComposables.remove (composable);
    }
    
    public void drawLine (ConnectionLine line) {
      if (line != null) {
    	_umlLines.add(line);
      }
    }
    
    public Vector getComposables () {
      return _umlComposables;
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