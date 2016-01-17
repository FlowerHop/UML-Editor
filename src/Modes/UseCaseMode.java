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
	  super.onReleased (e);
	  BasicObject newObject = new UseCaseObject (_releaseX, _releaseY, _width, _height);
	  _canvas.drawShape (newObject);
	  _canvas.repaint ();
	  
	  for (int i = 0; i < _selectedShapes.size(); i++) {
		_selectedShapes.remove(0);
      }
	  
	  _selectedShapes.add (newObject);
	}
}