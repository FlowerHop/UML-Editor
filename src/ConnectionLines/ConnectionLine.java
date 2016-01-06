package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import BasicObjects.BasicObject;
import BasicObjects.Port;

public class ConnectionLine {
	protected Port _head;
	protected Port _tail;
    
    public ConnectionLine (Port head, Port tail) {
      _head = head;
      _tail = tail;
    }
    
    public void paintLine (Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      
      g2D.fill(new Rectangle.Double (_head.getRelativeX () - _head.getPortSide ()/2, _head.getRelativeY () - _head.getPortSide ()/2, _head.getPortSide (), _head.getPortSide ()));
      g2D.fill(new Rectangle.Double (_tail.getRelativeX () - _tail.getPortSide ()/2, _tail.getRelativeY () - _tail.getPortSide ()/2, _tail.getPortSide (), _tail.getPortSide ()));
    }
}
