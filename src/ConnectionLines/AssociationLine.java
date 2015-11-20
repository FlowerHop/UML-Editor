package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import BasicObjects.UMLObject;

public class AssociationLine extends ConnectionLine {
	private Line2D _line;

	public AssociationLine (UMLObject from, UMLObject to) {
	  super (from, to);
	  _line = new Line2D.Double ();
	}

	@Override
	public void paintLine(Graphics g) {
	  Graphics2D g2D = (Graphics2D) g;
	  Point[] pairPorts = findShortestConnectionPorts ();
	  
	  _line.setLine (pairPorts [0].getX (), pairPorts [0].getY (),
			  pairPorts [1].getX (), pairPorts [1].getY ());
	  
	  g2D.draw (_line);
	}
}
