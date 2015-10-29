import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JPanel;




public class CanvasArea extends JPanel {
    private CanvasArea _canvas;
	private Vector _shapes = new Vector();
	private Shape _selectedShape;
	
    double w, h;
    
    
    Cursor curCursor;
    
    public CanvasArea() {
      _canvas = this;
      
     
      w = 100;
      h = 75;
      
      setBackground(Color.white);
      MyMouseListener listener = new MyMouseListener();
      addMouseListener(listener);
      addMouseMotionListener(listener);
    }
    
    public Shape getContainedShape(int x, int y) {
      Enumeration shapes = _shapes.elements();
      
      while (shapes.hasMoreElements()) {
    	  Shape each = (Shape) shapes.nextElement();
    	  if (each.contains(x, y)) {
    		  return each;
    	  }
      }
      
      return null;
    }
    
    
    public void paint(Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.clearRect(0, 0, (int)getBounds().getWidth(),(int) getBounds().getHeight());
      
      Enumeration shapes = _shapes.elements();
      while (shapes.hasMoreElements()) {
    	  Shape each = (Shape) shapes.nextElement();
    	  g2D.draw(each);
      }
      
      if (curCursor != null)
        setCursor(curCursor);
    }
    
    public void drawHighlightSquares(Graphics2D g2D, Rectangle2D r) {
      double x = r.getX();
      double y = r.getY();
      double w = r.getWidth();
      double h = r.getHeight();
      g2D.setColor(Color.black);
      g2D.fill(new Rectangle.Double(x - 3.0, y - 3.0, 6.0, 6.0));
      g2D.fill(new Rectangle.Double(x + w * 0.5 - 3.0, y - 3.0, 6.0, 6.0));
      g2D.fill(new Rectangle.Double(x + w - 3.0, y - 3.0, 6.0, 6.0));
      g2D.fill(new Rectangle.Double(x - 3.0, y + h * 0.5 - 3.0, 6.0, 6.0));
      g2D.fill(new Rectangle.Double(x + w - 3.0, y + h * 0.5 - 3.0, 6.0, 6.0));
      g2D.fill(new Rectangle.Double(x - 3.0, y + h - 3.0, 6.0, 6.0));
      g2D.fill(new Rectangle.Double(x + w * 0.5 - 3.0, y + h - 3.0, 6.0, 6.0));
      g2D.fill(new Rectangle.Double(x + w - 3.0, y + h - 3.0, 6.0, 6.0));
    }
    
    public void displayParameters(Shape shape) {
        double x = shape.getBounds().getX();
        double y = shape.getBounds().getY();
        double w = shape.getBounds().getWidth();
        double h = shape.getBounds().getHeight();
        String locString = "(" + Double.toString(x) + ","
            + Double.toString(y) + ")";
        String sizeString = "(" + Double.toString(w) + ","
            + Double.toString(h) + ")";
        System.out.println(locString);
        //location.setText(locString);
    }

    class MyMouseListener extends MouseAdapter {
        private int _pressX, _pressY;
  
        public void mouseMoved(MouseEvent e) {
      	  int clickX = e.getX();
          int clickY = e.getY();
      	  _selectedShape = getContainedShape(clickX, clickY);
          if (_selectedShape != null) { 
              curCursor = Cursor
                  .getPredefinedCursor(Cursor.HAND_CURSOR);
          } else {
              curCursor = Cursor.getDefaultCursor();
          }
          _canvas.repaint();
        }
        
	
        public void mousePressed(MouseEvent e) {
          _pressX = e.getX();
    	  _pressY = e.getY();
    	  
    	  _selectedShape = getContainedShape(_pressX, _pressY);
    	
    	  if (_selectedShape == null) {
    		_selectedShape = new Rectangle2D.Double(_pressX, _pressY, w, h);
    		_shapes.addElement(_selectedShape);		
    	  }
        
          _canvas.repaint();
        }
        
        public void mouseDragged(MouseEvent e) {
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
               
            _shapes.remove(_selectedShape);
            _selectedShape = new Rectangle2D.Double(originX + dragX, originY + dragY, originWidth, originHeight);
            _shapes.add(_selectedShape);
          }
        	  
          _canvas.repaint();
        }
        
        public void mouseReleased(MouseEvent e) {
//        if (ellipse.contains(e.getX(), e.getY())) {
//          
//          selectedShape = ellipse;
//
//          displayParameters(selectedShape);
//        }
//
//        canvas.repaint();
        }

        public void mouseClicked(MouseEvent e) {
    	  
//        if (ellipse.contains(e.getX(), e.getY())) {
//          selectedShape = ellipse;
//          
//
//          displayParameters(selectedShape);
//        } 
//        canvas.repaint();
    	  
        }
    }
    
    
//    class MyMouseMotionListener extends MouseMotionAdapter {
//      public void mouseDragged(MouseEvent e) {
////        if (ellipse.contains(e.getX(), e.getY())) {
////          
////          selectedShape = ellipse;
////          x2 = e.getX();
////          y2 = e.getY();
////          x = x + x2 - x1;
////          y = y + y2 - y1;
////          x1 = x2;
////          y1 = y2;
////        }
////        if (selectedShape != null)
////          displayParameters(selectedShape);
////        canvas.repaint();
//    	 
//    	  if (selectedShape != null) {
//    		
//            int x2 = e.getX();
//            int y2 = e.getY();
//            
//            
//            double originX = selectedShape.getBounds2D().getX();
//            double originY = selectedShape.getBounds2D().getY();
//            double originWidth = selectedShape.getBounds2D().getWidth();
//            double originHeight = selectedShape.getBounds2D().getHeight();
//            
//            double dragX = x2 - x1;
//            double dragY = y2 - y1;
//           
//            
//            x1 = x2;
//            y1 = y2;
//            
//            shapes.remove(selectedShape);
//      	    selectedShape = new Rectangle2D.Double(originX + dragX, originY + dragY, originWidth, originHeight);
//      	    shapes.add(selectedShape);
//    	  }
//    	  
//    	  canvas.repaint();
//      }
//
//      public void mouseMoved(MouseEvent e) {
//    	int clickX = e.getX();
//      	int clickY = e.getY();
//      	
//    	selectedShape = getContainedShape(clickX, clickY);
//        if (selectedShape != null) { 
//          
//            curCursor = Cursor
//                .getPredefinedCursor(Cursor.HAND_CURSOR);
//           
//        } else {
//            curCursor = Cursor.getDefaultCursor();
//        }
//        canvas.repaint();
//      }
//    }
  
}