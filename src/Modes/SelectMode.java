package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import BasicObjects.Composable;
import BasicObjects.Composite;
import BasicObjects.BasicObject;
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
  	  
  	  
      Enumeration cleanedObjects = _selectedComposables.elements ();
    	
      while (cleanedObjects.hasMoreElements ()) {
        Composable each = (Composable) cleanedObjects.nextElement ();
        each.setSelect (false);
      }
      
      _canvas.repaint ();
  	  
  	  _selectedComposables = _canvas.getContainedComposables (_pressX, _pressY);
	}

	@Override
	public void onDragged (MouseEvent e) {
	  if (!_selectedComposables.isEmpty ()) {
		Composable selectedComposable = findFrontFromComposables (_selectedComposables);
		int toX = e.getX ();
	    int toY = e.getY ();
	    int differenceX = toX - _pressX;             
	    int differenceY = toY - _pressY;
	    _pressX = toX;
	    _pressY = toY;
	    selectedComposable.move ((int) (differenceX), (int) (differenceY));
	    _canvas.repaint ();	
	  }
	}

	@Override
	public void onReleased (MouseEvent e) {
	  if (!_selectedComposables.isEmpty ()) {  
	  	Composable selectedComposable = findFrontFromComposables (_selectedComposables);
	  	_selectedComposables = new Vector ();
	  	selectedComposable.setSelect (true);
	  	_selectedComposables.add (selectedComposable);
	  } else {
		Rectangle2D bounding = new Rectangle2D.Double();
		bounding.setFrameFromDiagonal (_pressX, _pressY, e.getX (), e.getY ());
	
		Vector containedComposables = _canvas.getContainedComposables (bounding);  
		Enumeration objects = containedComposables.elements ();
		  
		while (objects.hasMoreElements ()) {
		  Composable each = (Composable) objects.nextElement ();
		  each.setSelect (true);
		}
		  
		_selectedComposables = containedComposables;  
	  }
	 
	  _canvas.repaint ();
	}

	@Override
	public void onMoved (MouseEvent e) {
	  int clickX = e.getX ();
      int clickY = e.getY ();
        
      Vector containedComposables = _canvas.getContainedComposables (clickX, clickY);
      
      if (!containedComposables.isEmpty ()) { 
        _canvas.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
      } else {
        _canvas.setCursor (Cursor.getDefaultCursor ());      
      }
	}
	

	@Override
	public void eidtName (String name) {
	  if (_selectedComposables.size () == 1) {
		Composable composable = (Composable) _selectedComposables.get (0);
		
		if (composable instanceof BasicObject) {
		  ((BasicObject) composable).setName (name);
		  _canvas.repaint ();
		}
	  }
	}
    
    @Override
    public void toGroup () {
      if (_selectedComposables.size () > 1) {
    	Enumeration objects = _selectedComposables.elements ();
        Composite newComposite = new Composite ();
          
        while (objects.hasMoreElements ()) {
          Composable each = (Composable) objects.nextElement ();
          newComposite.add (each);
          _canvas.removeComposable (each);
        }
        
        _canvas.drawComposable (newComposite);
      }
    }
    
    @Override
    public void toUnGroup () {
      if (_selectedComposables.size () == 1) {
    	
        Enumeration objects = _selectedComposables.elements ();
        Composable headComposable = (Composable) _selectedComposables.firstElement ();
        
        if (headComposable instanceof BasicObject) {
          return;
        }
        
        Composite headCompoite = (Composite) _selectedComposables.firstElement ();
        
        Vector composable = headCompoite.getAllComposable ();
        objects = composable.elements ();
        
        while (objects.hasMoreElements ()) {
          Composable each = (Composable) objects.nextElement ();
          _canvas.drawComposable (each);
        }
        
        _canvas.getComposables ().remove (headCompoite);
      }
    }
}
