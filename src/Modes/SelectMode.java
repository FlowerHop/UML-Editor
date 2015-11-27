package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import BasicObjects.UMLObject;
import UI.CanvasArea;




public class SelectMode extends Mode {
	private Vector _selectedUMLObjects = new Vector ();
	
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	private Vector _selectedComposite = new Vector ();
	
	public SelectMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed (MouseEvent e) {
	  _pressX = e.getX ();
  	  _pressY = e.getY ();
  	  
  	  if (_selectedUMLObjects != null) {
    	Enumeration cleanedObjects = _selectedUMLObjects.elements ();
    	
    	while (cleanedObjects.hasMoreElements ()) {
          UMLObject each = (UMLObject) cleanedObjects.nextElement ();
          each.setSelect (false);
    	}
      }
  	  
  	  _selectedUMLObjects = _canvas.getContainedUMLObjects (_pressX, _pressY);
	}

	@Override
	public void onDragged (MouseEvent e) {
	  UMLObject selectedUMLObject = findFrontFromUMLObjects (_selectedUMLObjects);
	  
	  if (selectedUMLObject != null) {
		  int toX = e.getX ();
	      int toY = e.getY ();
	      int differenceX = toX - _pressX;             
	      int differenceY = toY - _pressY;
	      double originX = selectedUMLObject.getX ();
	      double originY = selectedUMLObject.getY ();
	        
	      _pressX = toX;
	      _pressY = toY;
	      selectedUMLObject.moveTo ((int) (originX + differenceX), (int) (originY + differenceY));
	  }
       	  
      _canvas.repaint ();		
	}

	@Override
	public void onReleased (MouseEvent e) {
	  if (!_selectedUMLObjects.isEmpty ()) {  
	  	UMLObject selectedUMLObject = findFrontFromUMLObjects (_selectedUMLObjects);
	      
	  	_selectedUMLObjects = new Vector ();
	  	  
	  	if (selectedUMLObject != null) {
	  	  selectedUMLObject.setSelect (true);
	 	  _selectedUMLObjects.add (selectedUMLObject);
	  	}
	  } else {
		Rectangle2D bounding = new Rectangle2D.Double();
		bounding.setFrameFromDiagonal(_pressX, _pressY, e.getX (), e.getY ());
		  
		Vector containedUMLObjects = _canvas.getContainedUMLObjects (bounding);
		  
		Enumeration objects = containedUMLObjects.elements ();
		  
		while (objects.hasMoreElements ()) {
		  UMLObject each = (UMLObject) objects.nextElement ();
		  each.setSelect (true);
		}
		  
		_selectedUMLObjects = containedUMLObjects;  
	  }
	 
	  _canvas.repaint ();
	}

	@Override
	public void onMoved (MouseEvent e) {
	  int clickX = e.getX ();
      int clickY = e.getY ();
        
      Vector containedUMLObjects = _canvas.getContainedUMLObjects (clickX, clickY);
      
      if (!containedUMLObjects.isEmpty ()) { 
        _canvas.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
      } else {
        _canvas.setCursor (Cursor.getDefaultCursor ());      
      }
	}

	@Override
	public void eidtName(String name) {
	  if (_selectedUMLObjects != null && _selectedUMLObjects.size () == 1) {
		UMLObject object = (UMLObject) _selectedUMLObjects.get(0);
		object.setName (name);
		_canvas.repaint ();
	  }
	}

    private UMLObject findFrontFromUMLObjects (Vector objectsVector) {
      Enumeration objects = objectsVector.elements ();
      UMLObject result = null;
     
      while (objects.hasMoreElements ()) {
    	UMLObject each = (UMLObject) objects.nextElement ();
    	
    	if (result == null) {
    	  result = each;
    	} else if (each.getDepth () < result.getDepth ()) {
          result = each;
  	  	}
      }
      
      return result;
    }
}
