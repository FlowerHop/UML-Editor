package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import Shape.Shape;
import Shape.BasicObject;
import Shape.GroupObject;
import UI.CanvasArea;




public class SelectMode extends Mode {
	//  private int _pressX, _pressY;
	// private Vector _selectedComposables = new Vector ();
	
	public SelectMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed (MouseEvent e) {
      // clean the selected ports
	  Enumeration cleanedObjects = _selectedShapes.elements ();
    	
	  while (cleanedObjects.hasMoreElements ()) {
	    Shape each = (Shape) cleanedObjects.nextElement ();
	    each.setSelect (false);
	  }
	      
	  _canvas.repaint ();
	  
	  super.onPressed (e);
	}

	@Override
	public void onDragged (MouseEvent e) {
	  super.onDragged (e);
	}

	@Override
	public void onReleased (MouseEvent e) {
	  super.onReleased (e);
	  
	  if (!_selectedShapes.isEmpty ()) {  
	  	Shape selectedShape = findFrontFromShapes (_selectedShapes);
	  	_selectedShapes = new Vector ();
	  	selectedShape.setSelect (true);
	  	_selectedShapes.add (selectedShape);
	  } else {
		Rectangle2D bounding = new Rectangle2D.Double();
		bounding.setFrameFromDiagonal (_pressX, _pressY, _releaseX, _releaseY);
	
		Vector containedShapes = getContainedShapes (bounding);  
		Enumeration objects = containedShapes.elements ();
		  
		while (objects.hasMoreElements ()) {
		  Shape each = (Shape) objects.nextElement ();
		  each.setSelect (true);
		}
		  
		_selectedShapes = containedShapes;  
	  }
	 
	  _canvas.repaint ();
	}
}
