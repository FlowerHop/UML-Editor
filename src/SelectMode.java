import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;




public class SelectMode extends Mode {
	// maybe it doesn't need to be a global
	private UMLObject _selectedUMLObject;
	
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	
	public SelectMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	void onPressed (MouseEvent e) {
	  _pressX = e.getX ();
  	  _pressY = e.getY ();
  	  _selectedUMLObject = _canvas.getContainedUMLObject (_pressX, _pressY);
      
      _canvas.repaint ();
	}

	@Override
	void onDragged (MouseEvent e) {
	  if (_selectedUMLObject != null) {
        int toX = e.getX ();
        int toY = e.getY ();
   
        double originX = _selectedUMLObject.getX ();
        double originY = _selectedUMLObject.getY ();
        double originWidth = _selectedUMLObject.getWidth ();   
        double originHeight = _selectedUMLObject.getHeight ();
        
        int dragX = toX - _pressX;             
        int dragY = toY - _pressY;
              
        _pressX = toX;
        _pressY = toY;
               
        _selectedUMLObject = _canvas.moveUMLObject (_selectedUMLObject, originX + dragX, originY + dragY, originWidth, originHeight);
      }
        	  
      _canvas.repaint ();		
	}

	@Override
	void onReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	void onMoved (MouseEvent e) {
	  int clickX = e.getX ();
      int clickY = e.getY ();
        
      if (_canvas.getContainedUMLObject (clickX, clickY) != null) { 
        _canvas.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
      } else {
        _canvas.setCursor (Cursor.getDefaultCursor ());      
      }
		
	}
	
	@Override
	Rectangle2D getBounding () {
	  if (_selectedUMLObject != null) {
		return new Rectangle2D.Double(_selectedUMLObject.getX (), _selectedUMLObject.getY (), _selectedUMLObject.getWidth (), _selectedUMLObject.getHeight ());    
	  } else return null;
	}
}
