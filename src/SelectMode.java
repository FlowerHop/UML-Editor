import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;




public class SelectMode extends Mode {
	private Shape _selectedShape;
	private CanvasArea _canvas;
	private int _pressX, _pressY;
	
	int w = 100;
	int h = 75;

	public SelectMode(CanvasArea canvas) {
		_canvas = canvas;
		
	}
	
	@Override
	void onPressed(MouseEvent e) {
		// TODO Auto-generated method stub
	  System.out.println("SelectMode");
		
	  _pressX = e.getX();
  	  _pressY = e.getY();
  	  
  	  _selectedShape = _canvas.getContainedShape(_pressX, _pressY);
  	  if (_selectedShape != null) {
  		_canvas.drawConnectionPorts(_selectedShape);
  	  }
	}

	@Override
	void onDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	  if (_selectedShape != null) {
        int toX = e.getX();
        int toY = e.getY();
    
        double originX = _selectedShape.getBounds2D().getX();
        double originY = _selectedShape.getBounds2D().getY();
        double originWidth = _selectedShape.getBounds2D().getWidth();   
        double originHeight = _selectedShape.getBounds2D().getHeight();
        double dragX = toX - _pressX;             
        double dragY = toY - _pressY;
              
        _pressX = toX;
        _pressY = toY;
               
        _selectedShape = _canvas.moveShape(_selectedShape, originX + dragX, originY + dragY, originWidth, originHeight);

      }
        	  
      _canvas.repaint();		
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
