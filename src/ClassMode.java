import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class ClassMode extends Mode {
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	
	private final int _width = 100;
	private final int _height = 75;

	public ClassMode (CanvasArea canvas) {
	  _canvas = canvas;
	}
	
	@Override
	void onPressed (MouseEvent e) {
	  _pressX = e.getX ();
	  _pressY = e.getY ();
	
	  _canvas.drawObject (new ClassObject (_pressX, _pressY, _width, _height));
	  _canvas.repaint ();
	}

	@Override
	void onDragged (MouseEvent e) {
			
	}

	@Override
	void onReleased (MouseEvent e) {
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
        
        _canvas.repaint ();
	}

	@Override
	Rectangle2D getBounding () {
		return null;
	}
}