package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import BasicObjects.BasicObject;
import BasicObjects.Port;
import BasicObjects.Shape;

public class ConnectionLine implements Shape {
	protected Port _head;
	protected Port _tail;
    
    public ConnectionLine (Port head, Port tail) {
      _head = head;
      _tail = tail;
    }

	@Override
	public void paintObject (Graphics g) {
	  Graphics2D g2D = (Graphics2D) g;
	      
	  g2D.fill(new Rectangle.Double (_head.getRelativeX () - _head.getPortSide ()/2, _head.getRelativeY () - _head.getPortSide ()/2, _head.getPortSide (), _head.getPortSide ()));
	  g2D.fill(new Rectangle.Double (_tail.getRelativeX () - _tail.getPortSide ()/2, _tail.getRelativeY () - _tail.getPortSide ()/2, _tail.getPortSide (), _tail.getPortSide ()));
	}

	@Override
	public void move(int differenceX, int differenctY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Rectangle2D bounding) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSelect(boolean isSelect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDepth(int depth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
