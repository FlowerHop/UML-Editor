package Modes;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

import Shape.Shape;
import Shape.AssociationLine;
import Shape.BasicObject;
import Shape.CompositionLine;
import Shape.GeneralizationLine;
import Shape.Port;
import UI.CanvasArea;

public class GeneralizationMode extends Mode {
	// maybe it doesn't need to be a global
	private Vector _selectedUMLObjects = new Vector ();
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	private double _originUMLObjectX, _originUMLObjectY;

	public GeneralizationMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed(MouseEvent e) {
	  _pressX = e.getX();
	  _pressY = e.getY();
	  _selectedUMLObjects = _canvas.getContainedUMLObjects (_pressX, _pressY);
      
	  if (!_selectedUMLObjects.isEmpty ()) {
		BasicObject selectedObject = (BasicObject) findFrontFromComposables (_selectedUMLObjects);
		_originUMLObjectX = selectedObject.getX ();
        _originUMLObjectY = selectedObject.getY ();
	  }
      
      _canvas.repaint ();
	}

	@Override
	public void onDragged(MouseEvent e) {
	  if (!_selectedUMLObjects.isEmpty ()) {
		BasicObject selectedUMLObject = (BasicObject) findFrontFromComposables (_selectedUMLObjects);
		int toX = e.getX ();
	    int toY = e.getY ();
	    int differenceX = toX - _pressX;             
	    int differenceY = toY - _pressY;
	        
	    _pressX = toX;
	    _pressY = toY;
	        
	    selectedUMLObject.move (differenceX, differenceY);
	  } 
	        	  
	  _canvas.repaint ();	
	}

	@Override
	public void onReleased (MouseEvent e) {
	  if (!_selectedUMLObjects.isEmpty ()) {
		BasicObject selectedUMLObject = (BasicObject) findFrontFromComposables (_selectedUMLObjects);
		int releaseX = e.getX ();
		int releaseY = e.getY ();
		Vector containedUMLObjects = _canvas.getContainedUMLObjects (releaseX, releaseY);
		BasicObject releasedUMLObject = null;
		
		if (!containedUMLObjects.isEmpty ()) {
		  releasedUMLObject = (BasicObject) findFrontFromComposables (containedUMLObjects);
		  
		  while (releasedUMLObject == selectedUMLObject && !containedUMLObjects.isEmpty ()) {
			containedUMLObjects.remove (releasedUMLObject);
			releasedUMLObject = null;
			
			if (containedUMLObjects.isEmpty ()) {
			  break;
			}
			
			releasedUMLObject = (BasicObject) findFrontFromComposables (containedUMLObjects);
		  }
		}
		
		selectedUMLObject.move ((int) (_originUMLObjectX - selectedUMLObject.getX ()), (int) (_originUMLObjectY - selectedUMLObject.getY ()));
		
		if (releasedUMLObject != null) {
		  Port[] selectedUMLObjectConnectionPorts = selectedUMLObject.getConnectionPorts ();
		  Port[] releasedUMLObjectConnectionPorts = releasedUMLObject.getConnectionPorts ();
			  
		  Port head = null;
		  Port tail = null;
		  double minDistance = Double.MAX_VALUE;
			  
		  for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
			  Port from = selectedUMLObjectConnectionPorts[i];
			  Port to = releasedUMLObjectConnectionPorts[j];
			  double distance = from.distanceTo (to);
			  
			  if (distance < minDistance) {
				head = from;
		      	tail = to;
		      	minDistance = distance;
		      }
		    }
		  }
		  
		  _canvas.drawShape (new GeneralizationLine (head, tail));
		}
		
		_canvas.repaint ();
	  }
	}

	@Override
	public void onMoved(MouseEvent e) {
	  int clickX = e.getX ();
	  int clickY = e.getY ();
	        
      Vector containedComposables = _canvas.getContainedComposables (clickX, clickY);
      
      if (containedComposables.size () != 0) {
	    _canvas.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
	  } else {
	    _canvas.setCursor (Cursor.getDefaultCursor ());      
	  }
	}
	
	@Override
	public void eidtName(String name) {
	  if (_selectedUMLObjects.size () == 1) {
		Shape shape = (Shape) _selectedUMLObjects.get (0);
			
		if (shape instanceof BasicObject) {
		  ((BasicObject) shape).setName (name);
		  _canvas.repaint ();
		}
	  }
	}
}

