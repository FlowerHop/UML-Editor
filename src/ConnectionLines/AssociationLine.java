package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import BasicObjects.BasicObject;

public class AssociationLine extends ConnectionLine {
	private Line2D _line;

	public AssociationLine (BasicObject from, BasicObject to) {
	  super (from, to);
	  _line = new Line2D.Double ();
	}

	@Override
	public void paintLine(Graphics g) {
	  super.paintLine (g);
	  Graphics2D g2D = (Graphics2D) g;
	  
	  _line.setLine (_pairPorts[0].getX (), _pairPorts[0].getY (),
			  _pairPorts[1].getX (), _pairPorts[1].getY ());
	  
	  g2D.draw (_line);
	}
}
