package Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;


public class BasicObject implements Shape {
	private final int NORTH_PORT = 0;
	private final int EAST_PORT = 1;
	private final int WEST_PORT = 2;
	private final int SOUTH_PORT = 3;
	
	private final int PORT_NUMBER = 4;
	
	private final int DEFAULT_DEPTH = 99;
	private Rectangle2D _bounding;
	private boolean _isSelected;
	private Vector _children;
	private int _depth = DEFAULT_DEPTH;
	
	private Port[] _ports = new Port[PORT_NUMBER];
	
	public BasicObject (double posX, double posY, double width, double height) {
	  _bounding = new Rectangle2D.Double (posX, posY, width, height);
	  _children = new Vector ();
	  initConnectionPorts ();
    }
	
	public void paintObject (Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
	  g2D.clearRect ((int) _bounding.getX (), (int) _bounding.getY (), (int) _bounding.getWidth (), (int) _bounding.getHeight ());
	
	  if (_isSelected) {
		for (int i = 0; i < 4; i++) {
		  Port port = _ports[i];
	      g2D.fill(new Rectangle.Double (port.getRelativeX() - port.getPortSide ()/2, port.getRelativeY() - port.getPortSide ()/2, port.getPortSide (), port.getPortSide ()));
		  System.out.println (port.getRelativeX() + ", " + port.getRelativeY ());
		}
	  }
	}
	
	public void move (int differenceX, int differenceY) {
      double originX = _bounding.getX ();
      double originY = _bounding.getY ();
	  double width = _bounding.getWidth ();
	  double height = _bounding.getHeight ();
	  _bounding.setFrame (originX + differenceX, originY + differenceY, (int) width, (int) height);
	  
	  for (int i = 0; i < PORT_NUMBER; i++) {
		_ports[i].move (differenceX, differenceY);
	  }
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
	  
	  if (bounding.contains (topRight) && bounding.contains(topLeft) && bounding.contains(bottomLeft) && bounding.contains(bottomRight)) {
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
	
	public void initConnectionPorts () {
	  _ports[NORTH_PORT] = new Port (getX () + getWidth ()*0.5, getY ());
	  _ports[EAST_PORT] = new Port (getX () + getWidth (), getY () + getHeight ()*0.5);
	  _ports[WEST_PORT] = new Port (getX (), getY () + getHeight ()*0.5);
	  _ports[SOUTH_PORT] = new Port (getX () + getWidth ()*0.5, getY () + getHeight ());
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
    
    public Port[] getConnectionPorts () {
      return _ports;
    }
}
