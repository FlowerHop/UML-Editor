package BasicObjects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

public class GroupObject implements Shape {
	private final int DEFAULT_DEPTH = 100;
    private Vector _composite = new Vector ();
    private int _depth = DEFAULT_DEPTH;
    
	public GroupObject () {
		
	}
	
	public void add (Shape composable) {
	  _composite.add (composable);
	}
	
	public void remove (GroupObject composite) {
	  Enumeration objects = composite.getAllComposable ().elements ();
		  
	  while (objects.hasMoreElements ()) {
		Shape each = (Shape) objects.nextElement ();
		_composite.add(each);
	  }
		
	  _composite.remove(composite);
	}
	
	@Override
	public void move (int differenceX, int differenceY) {
	  Enumeration objects = _composite.elements ();
	  
	  while (objects.hasMoreElements ()) {
		Shape each = (Shape) objects.nextElement ();
		each.move (differenceX, differenceY);
	  }
	  
	}

	@Override
	public boolean contains(int x, int y) {
	  Enumeration objects = _composite.elements ();
		  
	  while (objects.hasMoreElements ()) {
	    Shape each = (Shape) objects.nextElement ();
	    
	    if (each.contains (x, y)) {
	      return true;
	    }
	  }
	  
	  return false;
	}

	@Override
	public boolean contains(Rectangle2D bounding) {
	  Enumeration objects = _composite.elements ();
		  
	  while (objects.hasMoreElements ()) {
		Shape each = (Shape) objects.nextElement ();
		
		if (each.contains (bounding)) {
		      return true;
		}
      }
	  return false;
	}

	@Override
	public void setSelect(boolean isSelect) {
	  Enumeration objects = _composite.elements ();
		  
	  while (objects.hasMoreElements ()) {
		Shape each = (Shape) objects.nextElement ();
		each.setSelect (isSelect);
	  }
	}

	@Override
	public void paintObject(Graphics g) {
	  Enumeration objects = _composite.elements ();
		  
	  while (objects.hasMoreElements ()) {
	    Shape each = (Shape) objects.nextElement ();
		each.paintObject (g);
	  }
	}

	@Override
	public void setDepth(int depth) {
	  _depth = depth;
	}

	@Override
	public int getDepth() {
	  return _depth;
	}

	@Override
	public double getX() {
	  double x = Integer.MAX_VALUE;
	  Enumeration objects = _composite.elements ();
	  
	  while (objects.hasMoreElements ()) {
		Shape each = (Shape) objects.nextElement ();
	    double eachX = each.getX ();
	    
		if (eachX < x) {
		  x = eachX;
		}
	  }
	  
	  return x;
	}
	

	@Override
	public double getY() {
	  double y = Integer.MAX_VALUE;
	  Enumeration objects = _composite.elements ();
		  
	  while (objects.hasMoreElements ()) {
		Shape each = (Shape) objects.nextElement ();
		double eachY = each.getY ();
		    
		if (eachY < y) {
		  y = eachY;
		}
	  }
		  
	  return y;
	}
	
	public Vector getAllComposable () {
	  return _composite;
	}
}
