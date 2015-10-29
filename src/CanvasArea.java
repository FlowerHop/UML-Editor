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
	private Mode _mode;
	
    double w, h;
    
    
    Cursor curCursor;
    
    public CanvasArea() {
      _canvas = this;
      _mode = new SelectMode(_canvas);
      
      w = 100;
      h = 75;
      
      setBackground(Color.white);
      MyMouseListener listener = new MyMouseListener();
      addMouseListener(listener);
      addMouseMotionListener(listener);
    }
    
    public void changeMode(Mode mode) {
    	_mode = mode;
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

    public void drawShape(Shape shape) {
      if (shape != null)
        _shapes.addElement(shape);
    }
    
    public Shape moveShape(Shape shape, double toX, double toY, double width, double height) {
      _shapes.remove(shape);
      shape = new Rectangle2D.Double(toX, toY, width, height);
      _shapes.addElement(shape);
      return shape;
    }
    class MyMouseListener extends MouseAdapter {

        public void mouseMoved(MouseEvent e) {
          _mode.onMoved(e); 
        }
        
	
        public void mousePressed(MouseEvent e) {
          _mode.onPressed(e);
        }
        
        public void mouseDragged(MouseEvent e) {
          _mode.onDragged(e);
        }
        
        public void mouseReleased(MouseEvent e) {
          _mode.onReleased(e);
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
    
    
//    
}