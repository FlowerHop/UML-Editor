package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import BasicObjects.UMLObject;

public class ConnectionLine {
	private UMLObject _from;
	private UMLObject _to;
       
    public ConnectionLine (UMLObject from, UMLObject to) {
      _from = from;
      _to = to;
    }
    
    public void paintLine (Graphics g) {
      System.out.println ("Paint Line");
    }
    
    protected Point[] findShortestConnectionPorts () {
      Point[] toConnectionPorts = _to.getConnectionPorts ();
      Point[] fromConnectionPorts = _from.getConnectionPorts ();
        
  	  Point[] result = new Point[2];
  	  double minDistance = -1;
  	  
      for (int i = 0; i < 4; i++) {
      	for (int j = 0; j < 4; j++) {
      	  double x1 = fromConnectionPorts[j].getX();
       	  double y1 = fromConnectionPorts[j].getY();
      	  double x2 = toConnectionPorts[i].getX();
      	  double y2 = toConnectionPorts[i].getY();
      	  double distance = Math.sqrt ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
      	  if (minDistance == -1 || distance < minDistance) {
      		result[0] = new Point ((int) x1, (int) y1);
      	    result[1] = new Point ((int) x2, (int) y2);
      		minDistance = distance;
      	  }
      	}
      }
        
      return result;
    }
}
