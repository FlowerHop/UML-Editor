import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


public class UMLObject {
	
	private Rectangle2D _bounding;
	
	public UMLObject(double posX, double posY, double width, double height) {
	  _bounding = new Rectangle2D.Double(posX, posY, width, height);
    }
	
	public void paintObject(Graphics g) {
	  System.out.println("UML paint");
	}
	
	public void moveTo(int x, int y) {
	  double width = _bounding.getWidth();
	  double height = _bounding.getHeight();
	  _bounding.setFrame(x, y, (int) width, (int) height);
	} 
	
	public boolean contains(int x, int y) {
	  if (_bounding.contains(x, y)) {
		return true;
	  } else return false;
	}
	
	public double getX () {
		return _bounding.getX();
	}
	
	public double getY () {
		return _bounding.getY();
	}
	
	public double getWidth () {
		return _bounding.getWidth();
	}
	
	public double getHeight () {
		return _bounding.getHeight();
	}
}
