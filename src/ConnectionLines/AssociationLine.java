package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import BasicObjects.BasicObject;
import BasicObjects.Port;

public class AssociationLine extends ConnectionLine {
	private Line2D _line;

	public AssociationLine (Port from, Port to) {
	  super (from, to);
	  _line = new Line2D.Double ();
	}

	@Override
	public void paintObject (Graphics g) {
	  super.paintObject (g);
	  Graphics2D g2D = (Graphics2D) g;
	  
	  _line.setLine (_head.getRelativeX (), _head.getRelativeY (),
			  _tail.getRelativeX (), _tail.getRelativeY ());
	  
	  g2D.draw (_line);
	}
}
