package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import Shape.BasicObject;
import Shape.GroupObject;
import Shape.Shape;
import UI.CanvasArea;

public abstract class Mode {
	protected CanvasArea _canvas;
	protected Vector _selectedShapes = new Vector ();
	protected int _pressX, _pressY;
	protected int _releaseX, _releaseY;
	
    public void onPressed (MouseEvent e) {
      _pressX = e.getX ();
      _pressY = e.getY ();
      _selectedShapes = getContainedShapes (_pressX, _pressY);
      _canvas.repaint ();
    }
    
    public void onDragged (MouseEvent e) {
      if (!_selectedShapes.isEmpty ()) {
        Shape selectedShape = findFrontFromShapes (_selectedShapes);
    	int toX = e.getX ();
    	int toY = e.getY ();
    	int differenceX = toX - _pressX;             
    	int differenceY = toY - _pressY;
    	_pressX = toX;
    	_pressY = toY;
    	selectedShape.move ((int) (differenceX), (int) (differenceY));
    	_canvas.repaint ();	
      }
    }
    
    public void onReleased (MouseEvent e) {
      _releaseX = e.getX ();
      _releaseY = e.getY ();
    };
    
    public void onMoved (MouseEvent e) {
      int clickX = e.getX ();
      int clickY = e.getY ();
       
      Vector containedShapes = getContainedShapes (clickX, clickY);
        
      if (!containedShapes.isEmpty ()) {
        _canvas.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
      } else {
        _canvas.setCursor (Cursor.getDefaultCursor ());      
      }
        
      _canvas.repaint ();
    };
    
    public void eidtName (String name) {
      if (_selectedShapes.size () == 1) {
    	Shape selectedShape = (Shape) _selectedShapes.get (0);
    		
    	if (selectedShape instanceof BasicObject) {
    	  ((BasicObject) selectedShape).setName (name);
    	  _canvas.repaint ();
        }
      }
    };
    
    public void toGroup () {
      if (_selectedShapes.size () > 1) {
        Enumeration objects = _selectedShapes.elements ();
        GroupObject newGroupObject = new GroupObject ();
              
        while (objects.hasMoreElements ()) {
          Shape each = (Shape) objects.nextElement ();
          newGroupObject.add (each);
          _canvas.removeShape (each);
        }
            
        _canvas.drawShape (newGroupObject);
      }
    }
    
    public void toUnGroup () {
      if (_selectedShapes.size () == 1) {
        Enumeration objects = _selectedShapes.elements ();
        Shape headShape = (Shape) _selectedShapes.firstElement ();
          
        if (headShape instanceof BasicObject) {
          return;
        }
          
        GroupObject headGroupObjects = (GroupObject) _selectedShapes.firstElement ();
          
        Vector shapes = headGroupObjects.getAllShapes ();
        objects = shapes.elements ();
          
        while (objects.hasMoreElements ()) {
          Shape each = (Shape) objects.nextElement ();
          _canvas.drawShape (each);
        }
          
        _canvas.removeShape (headGroupObjects);
      }
    }
    
    protected Shape findFrontFromShapes (Vector shapes) {
      Enumeration objects = shapes.elements ();
      Shape result = new GroupObject ();
       
      while (objects.hasMoreElements ()) {
      	Shape each = (Shape) objects.nextElement ();
      	
      	if (each.getDepth () < result.getDepth ()) {
          result = each;
    	}
      }
        
      return result;
    }

    protected Vector getContainedShapes (int x, int y) {
      Vector shapes = _canvas.getShapes ();
      Vector result = new Vector ();
      Enumeration objects = shapes.elements ();
          
      while (objects.hasMoreElements ()) {
        Shape each = (Shape) objects.nextElement ();
          
      	if (each.contains (x, y)) {
          result.add(each);
        }
      }
        
      return result;
    }
    
    protected Vector getContainedShapes (Rectangle2D bounding) {
      Vector shapes = _canvas.getShapes ();
      Vector result = new Vector ();
      Enumeration objects = shapes.elements ();
          
      while (objects.hasMoreElements ()) {
        Shape each = (Shape) objects.nextElement ();
          if (each.contains (bounding)) {
        	result.add(each);
          }
        }
           
      return result;
    }
      
    protected Vector getContainedBasicObjects (int x, int y) {
      Vector shapes = _canvas.getShapes ();
      Vector result = new Vector ();
      Enumeration objects = shapes.elements ();
              
      while (objects.hasMoreElements ()) {
        Shape each = (Shape) objects.nextElement ();
              
        if (each.contains (x, y) && each instanceof BasicObject) {
          result.add(each);
        }
      }
            
      return result;
    }
      
    protected Vector getContainedBasicObjects (Rectangle2D bounding) {
      Vector shapes = _canvas.getShapes ();
      Vector result = new Vector ();
      Enumeration objects = shapes.elements ();
            
      while (objects.hasMoreElements ()) {
        Shape each = (Shape) objects.nextElement ();
        if (each.contains (bounding) && each instanceof BasicObject) {
          result.add(each);
        }
      }
             
      return result;
    }
}
