package BasicObjects;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;


public class UMLObject {
	private Rectangle2D _bounding;
	
	public UMLObject (double posX, double posY, double width, double height) {
	  _bounding = new Rectangle2D.Double (posX, posY, width, height);
    }
	
	public void paintObject (Graphics g) {
	  System.out.println ("UML paint");
	}
	
	public void moveTo (int x, int y) {
	  double width = _bounding.getWidth ();
	  double height = _bounding.getHeight ();
	  _bounding.setFrame (x, y, (int) width, (int) height);
	} 
	
	public boolean contains (int x, int y) {
	  if (_bounding.contains (x, y)) {
		return true;
	  } else return false;
	}
	
	public double getX () {
		return _bounding.getX ();
	}
	
	public double getY () {
		return _bounding.getY ();
	}
	
	public double getWidth () {
		return _bounding.getWidth ();
	}
	
	public double getHeight () {
		return _bounding.getHeight ();
	}
	
	public Point[] getConnectionPorts () {
	  Point top = new Point ((int) (getX () + getWidth ()*0.5), (int) (getY ()));
	  Point left = new Point ((int) (getX ()), (int) (getY () + getHeight ()*0.5));
	  Point right = new Point ((int) (getX () + getWidth ()), (int) (getY () + getHeight ()*0.5));
	  Point bottom = new Point ((int) (getX () + getWidth ()*0.5), (int) (getY () + getHeight ()));
	  
	  return new Point[] {top, left, right, bottom};
	}
}
