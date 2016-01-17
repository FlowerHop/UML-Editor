package Modes;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

import Shape.GroupObject;
import Shape.Shape;
import Shape.AssociationLine;
import Shape.BasicObject;
import Shape.CompositionLine;
import Shape.GeneralizationLine;
import Shape.Port;
import UI.CanvasArea;

public class CompositionMode extends Mode {
	// maybe it doesn't need to be a global
	// private Vector _selectedUMLObjects = new Vector ();
	// private int _pressX, _pressY;
	private double _originShapeX, _originShapeY;

	public CompositionMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed(MouseEvent e) {
      super.onPressed (e);	  
      
	  if (!_selectedShapes.isEmpty ()) {
		Shape selectedShape = findFrontFromShapes (_selectedShapes);
		_originShapeX = selectedShape.getX ();
		_originShapeY = selectedShape.getY ();
        _canvas.repaint ();
	  }
	}

	@Override
	public void onDragged(MouseEvent e) {
	  super.onDragged (e);
	}

	@Override
	public void onReleased (MouseEvent e) {
	  super.onReleased (e);
	  
	  if (!_selectedShapes.isEmpty ()) {
		Shape selectedShape = findFrontFromShapes (_selectedShapes);  
		BasicObject selectedBasicObject = null;
		BasicObject releasedBasicObject = null;
		
		if (!(selectedShape instanceof GroupObject)) {
		  selectedBasicObject = (BasicObject) findFrontFromShapes (_selectedShapes);
		  Vector containedBasicObjects = getContainedBasicObjects (_releaseX, _releaseY);
			
		  if (!containedBasicObjects.isEmpty ()) {
			releasedBasicObject = (BasicObject) findFrontFromShapes (containedBasicObjects);
			  
			while (releasedBasicObject == selectedBasicObject && !containedBasicObjects.isEmpty ()) {
			  containedBasicObjects.remove (releasedBasicObject);
			  releasedBasicObject = null;
				
			  if (containedBasicObjects.isEmpty ()) {
				break;
		      }
				
			  releasedBasicObject = (BasicObject) findFrontFromShapes (containedBasicObjects);
			}
		  }
		}
	    
		selectedShape.move ((int) (_originShapeX - selectedShape.getX ()), (int) (_originShapeY - selectedShape.getY ()));
		
		if (releasedBasicObject != null) {
		  Port[] selectedBasicObjectConnectionPorts = selectedBasicObject.getConnectionPorts ();
		  Port[] releasedBasicObjectConnectionPorts = releasedBasicObject.getConnectionPorts ();
			  
		  Port head = null;
		  Port tail = null;
		  double minDistance = Double.MAX_VALUE;
			  
		  for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
			  Port from = selectedBasicObjectConnectionPorts[i];
			  Port to = releasedBasicObjectConnectionPorts[j];
			  double distance = from.distanceTo (to);
			  if (distance < minDistance) {
				head = from;
		      	tail = to;
		      	minDistance = distance;
		      }
			}
		  }
			
		  _canvas.drawShape (new CompositionLine (head, tail));
		}
		
		_canvas.repaint ();
	  }
	}
}