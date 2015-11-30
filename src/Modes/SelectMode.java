package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import BasicObjects.Composable;
import BasicObjects.Composite;
import BasicObjects.UMLObject;
import UI.CanvasArea;




public class SelectMode extends Mode {
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	private Vector _selectedComposables = new Vector ();
	
	public SelectMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed (MouseEvent e) {
	  _pressX = e.getX ();
  	  _pressY = e.getY ();
  	  
  	  if (_selectedComposables != null) {
    	Enumeration cleanedObjects = _selectedComposables.elements ();
    	
    	while (cleanedObjects.hasMoreElements ()) {
          Composable each = (Composable) cleanedObjects.nextElement ();
          each.setSelect (false);
    	}
      }
  	  
  	  _selectedComposables = _canvas.getContainedUMLObjects (_pressX, _pressY);
	}

	@Override
	public void onDragged (MouseEvent e) {
	  Composable selectedUMLObject = findFrontFromUMLObjects (_selectedComposables);
	  
	  if (selectedUMLObject != null) {
		  int toX = e.getX ();
	      int toY = e.getY ();
	      int differenceX = toX - _pressX;             
	      int differenceY = toY - _pressY;
	      double originX = selectedUMLObject.getX ();
	      double originY = selectedUMLObject.getY ();
	        
	      _pressX = toX;
	      _pressY = toY;
	      selectedUMLObject.moveTo ((int) (differenceX), (int) (differenceY));
	  }
       	  
      _canvas.repaint ();		
	}

	@Override
	public void onReleased (MouseEvent e) {
	  if (!_selectedComposables.isEmpty ()) {  
	  	Composable selectedUMLObject = findFrontFromUMLObjects (_selectedComposables);
	      
	  	_selectedComposables = new Vector ();
	  	  
	  	if (selectedUMLObject != null) {
	  	  selectedUMLObject.setSelect (true);
	  	  _selectedComposables.add (selectedUMLObject);
	  	}
	  } else {
		Rectangle2D bounding = new Rectangle2D.Double();
		bounding.setFrameFromDiagonal(_pressX, _pressY, e.getX (), e.getY ());
		  
		Vector containedUMLObjects = _canvas.getContainedUMLObjects (bounding);
		  
		Enumeration objects = containedUMLObjects.elements ();
		  
		while (objects.hasMoreElements ()) {
		  Composable each = (Composable) objects.nextElement ();
		  each.setSelect (true);
		}
		  
		_selectedComposables = containedUMLObjects;  
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
	  if (_selectedComposables != null && _selectedComposables.size () == 1) {
		UMLObject object = (UMLObject) _selectedComposables.get(0);
		object.setName (name);
		_canvas.repaint ();
	  }
	}

    private Composable findFrontFromUMLObjects (Vector objectsVector) {
      Enumeration objects = objectsVector.elements ();
      Composable result = null;
     
      while (objects.hasMoreElements ()) {
    	Composable each = (Composable) objects.nextElement ();
    	
    	if (result == null) {
    	  result = each;
    	} else if (each.getDepth () < result.getDepth ()) {
          result = each;
  	  	}
      }
      
      return result;
    }
    
    @Override
    public void toGroup () {
      Vector _totalComposables = _canvas.getComposables ();
      Enumeration objects = _selectedComposables.elements ();
      Composite newComposite = new Composite ();
      
      while (objects.hasMoreElements ()) {
        Composable each = (Composable) objects.nextElement ();
        newComposite.add (each);
        _totalComposables.remove (each);
      }
    
      _canvas.drawObject (newComposite);
    }
    
    @Override
    public void toUnGroup () {
      if (_selectedComposables.size () == 1) {
    	
        Enumeration objects = _selectedComposables.elements ();
        Composite object = (Composite) objects.nextElement();
        
        Vector composable = object.getAllComposable ();
        objects = composable.elements ();
        
        while (objects.hasMoreElements ()) {
          Composable each = (Composable) objects.nextElement ();
          _canvas.drawObject (each);
        }
        
        _canvas.getComposables ().remove (object);
        
      }

    }

	
}
