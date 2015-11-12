import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class ClassMode extends Mode {
	// maybe it doesn't need to be a global
	private Shape _selectedShape;
	
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	
	int w = 100;
	int h = 75;

	public ClassMode(CanvasArea canvas) {
	  _canvas = canvas;
		
	}
	
	@Override
	void onPressed(MouseEvent e) {
		// TODO Auto-generated method stub
	  System.out.println("ClassMode");
		
//	  _pressX = e.getX();
//  	  _pressY = e.getY();
//  	  
//  	  _selectedShape = _canvas.getContainedShape(_pressX, _pressY);
//  	
//  	  if (_selectedShape == null) {
//  		_selectedShape = new Rectangle2D.Double(_pressX, _pressY, w, h);
//  		_canvas.drawShape(_selectedShape);		
//  	  }
//      
//      _canvas.repaint();
	  _pressX = e.getX();
	  _pressY = e.getY();
	  
	  _canvas.drawShape(new Rectangle2D.Double(_pressX, _pressY, w, h));
	  _canvas.repaint();
		
	}

	@Override
	void onDragged(MouseEvent e) {
			
	}

	@Override
	void onReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void onMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int clickX = e.getX();
        int clickY = e.getY();
        
    	_selectedShape = _canvas.getContainedShape(clickX, clickY);
        if (_selectedShape != null) { 
            _canvas.curCursor = Cursor
                .getPredefinedCursor(Cursor.HAND_CURSOR);
        } else {
            _canvas.curCursor = Cursor.getDefaultCursor();
        }
        _canvas.repaint();
		
	}

	

	

}