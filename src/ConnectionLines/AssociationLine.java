package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
	  super.paintLine (g);
	  Graphics2D g2D = (Graphics2D) g;
	  
	  _line.setLine (_pairPoints [0].getX (), _pairPoints [0].getY (),
			  _pairPoints [1].getX (), _pairPoints [1].getY ());
	  
	  g2D.draw (_line);
	}
}
