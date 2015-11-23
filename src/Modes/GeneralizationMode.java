package Modes;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

import BasicObjects.UMLObject;
import ConnectionLines.AssociationLine;
import ConnectionLines.GeneralizationLine;
import UI.CanvasArea;

public class GeneralizationMode extends Mode {
	// maybe it doesn't need to be a global
	private UMLObject _pressedUMLObject;
	
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
	  Vector containedUMLObjects = _canvas.getContainedUMLObjects (_pressX, _pressY);
      
	  if (!containedUMLObjects.isEmpty ())
	      _pressedUMLObject = (UMLObject) containedUMLObjects.get(0);
      
      if (_pressedUMLObject != null) {
        _originUMLObjectX = _pressedUMLObject.getX();
        _originUMLObjectY = _pressedUMLObject.getY();
      }
      
      _canvas.repaint ();
	}

	@Override
	public void onDragged(MouseEvent e) {
	  if (_pressedUMLObject != null) {
		  int toX = e.getX ();
	      int toY = e.getY ();
	      int dragX = toX - _pressX;             
	      int dragY = toY - _pressY;
	      double originX = _pressedUMLObject.getX ();
	      double originY = _pressedUMLObject.getY ();
	        
	      _pressX = toX;
	      _pressY = toY;
	        
	      _pressedUMLObject.moveTo ((int) (originX + dragX), (int) (originY + dragY));
	    }
	        	  
	    _canvas.repaint ();	
	}

	@Override
	public void onReleased (MouseEvent e) {
	  if (_pressedUMLObject != null) {
		int releaseX = e.getX ();
		int releaseY = e.getY ();
		Vector containedUMLObjects = _canvas.getContainedUMLObjects (releaseX, releaseY);
		Enumeration objects = containedUMLObjects.elements ();
		
		UMLObject _releasedUMLObject = null;
		
		while (objects.hasMoreElements ()) {
		  UMLObject each = (UMLObject) objects.nextElement ();
		  if (each != _pressedUMLObject) {
			_releasedUMLObject = each;
			break;
		  }
		}
		
		_pressedUMLObject.moveTo ((int) _originUMLObjectX, (int) _originUMLObjectY);
		
		if (_releasedUMLObject != null) {
		  // create Generalization Line
		  _canvas.drawLine (new GeneralizationLine (_pressedUMLObject, _releasedUMLObject));
		}
		
		_canvas.repaint ();
	  }
	}

	@Override
	public void onMoved(MouseEvent e) {
	  int clickX = e.getX ();
	  int clickY = e.getY ();
	        
      Vector containedUMLObjects = _canvas.getContainedUMLObjects (clickX, clickY);
      
      if (containedUMLObjects.size () != 0) {
	    _canvas.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
	  } else {
	    _canvas.setCursor (Cursor.getDefaultCursor ());      
	  }
	}
}

