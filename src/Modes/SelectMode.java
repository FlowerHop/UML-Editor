package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import BasicObjects.UMLObject;
import UI.CanvasArea;




public class SelectMode extends Mode {
	// maybe it doesn't need to be a global
	private UMLObject _selectedUMLObject;
	
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	
	public SelectMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed (MouseEvent e) {
	  _pressX = e.getX ();
  	  _pressY = e.getY ();
  	  Vector containedUMLObjects = _canvas.getContainedUMLObjects (_pressX, _pressY);
  	  
  	  if (!containedUMLObjects.isEmpty ())
  	    _selectedUMLObject = (UMLObject) containedUMLObjects.get(0);
      
      _canvas.repaint ();
	}

	@Override
	public void onDragged (MouseEvent e) {
	  if (_selectedUMLObject != null) {
        int toX = e.getX ();
        int toY = e.getY ();
        int dragX = toX - _pressX;             
        int dragY = toY - _pressY;
        double originX = _selectedUMLObject.getX ();
        double originY = _selectedUMLObject.getY ();
        
        _pressX = toX;
        _pressY = toY;
        
        _selectedUMLObject.moveTo ((int) (originX + dragX), (int) (originY + dragY));
      }
        	  
      _canvas.repaint ();		
	}

	@Override
	public void onReleased(MouseEvent e) {
		// TODO Auto-generated method stub
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
	public Point[] getConnectionPorts () {
	  if (_selectedUMLObject != null) {
		return _selectedUMLObject.getConnectionPorts();    
	  } else return null;
	}
}
