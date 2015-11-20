package Modes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import BasicObjects.UseCaseObject;
import UI.CanvasArea;


public class UseCaseMode extends Mode {
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	
	private final int _width = 100;
	private final int _height = 75;

	public UseCaseMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	public void onPressed (MouseEvent e) {
	  _pressX = e.getX ();
	  _pressY = e.getY ();
	  
	  _canvas.drawObject (new UseCaseObject (_pressX, _pressY, _width, _height));
	  _canvas.repaint ();
	}

	@Override
	public void onDragged (MouseEvent e) {
		
	}

	@Override
	public void onReleased (MouseEvent e) {
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
        
        _canvas.repaint ();
	}
}