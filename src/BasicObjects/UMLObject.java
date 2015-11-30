package BasicObjects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;


public class UMLObject implements Composable {
	private final double PORTS_SIDE = 6;
	private final int DEFAULT_DEPTH = 99;
	private Rectangle2D _bounding;
	private boolean _isSelected;
	private Vector _children;
	private int _depth = DEFAULT_DEPTH;
	
	public UMLObject (double posX, double posY, double width, double height) {
	  _bounding = new Rectangle2D.Double (posX, posY, width, height);
	  _children = new Vector ();
    }
	
	public void paintObject (Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
	  g2D.clearRect ((int) _bounding.getX (), (int) _bounding.getY (), (int) _bounding.getWidth (), (int) _bounding.getHeight ());
	
	  if (_isSelected) {
		Point[] ports = getConnectionPorts ();
		
		for (int i = 0; i < 4; i++) {
		  Point port = ports[i];
	      g2D.fill(new Rectangle.Double (port.getX() - PORTS_SIDE/2, port.getY() - PORTS_SIDE/2, PORTS_SIDE, PORTS_SIDE));
		}
	  }
	}
	
	public void moveTo (int x, int y) {
      double originX = _bounding.getX ();
      double originY = _bounding.getY ();
	  double width = _bounding.getWidth ();
	  double height = _bounding.getHeight ();
	  _bounding.setFrame (originX + x, originY + y, (int) width, (int) height);
	} 
	
	public boolean contains (int x, int y) {
	  if (_bounding.contains (x, y)) {
		return true;
	  } else return false;
	}
	
	public boolean contains (Rectangle2D bounding) {
	  Point topLeft = new Point ((int) getX (), (int) getY ());
	  Point topRight = new Point ((int) (getX () + getWidth ()), (int) getY ());
	  Point bottomLeft = new Point ((int) getX (), (int) (getY () + getHeight ()));
	  Point bottomRight = new Point ((int) (getX () + getWidth ()), (int) (getY () + getHeight ()));
	  
	  if (bounding.contains(topRight) && bounding.contains(topLeft) && bounding.contains(bottomLeft) && bounding.contains(bottomRight)) {
		return true;
	  }
	  
	  return false;
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

    public void setSelect (boolean isSelect) {
      _isSelected = isSelect;
    }
    
    public void setName (String name) {
      
    }

    public int getDepth () {
      return _depth;
    }
    
    public void setDepth (int depth) {
      _depth = depth;
    }
    
}
