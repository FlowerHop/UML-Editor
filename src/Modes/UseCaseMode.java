package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import Shape.BasicObject;
import Shape.ClassObject;
import Shape.UseCaseObject;
import UI.CanvasArea;


public class UseCaseMode extends Mode {
	private CanvasArea _canvas;
	private BasicObject _selectedUMLObject = new UseCaseObject (0, 0, 0, 0);
	
	private final int _width = 100;
	private final int _height = 75;

	public UseCaseMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed (MouseEvent e) {
		
	}

	@Override
	public void onDragged (MouseEvent e) {
		
	}
	
	@Override
	public void onReleased (MouseEvent e) {
	  _selectedUMLObject = new UseCaseObject (e.getX (), e.getY (), _width, _height);
	  _canvas.drawShape (_selectedUMLObject);
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
        
        _canvas.repaint ();
	}
	
	@Override
	public void eidtName(String name) {
      _selectedUMLObject.setName (name);
	  _canvas.repaint ();
	}
}