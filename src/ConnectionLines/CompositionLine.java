package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;

import BasicObjects.BasicObject;
import ConnectionLines.GeneralizationLine.GeneralizationLinePath;

public class CompositionLine extends ConnectionLine {
	public CompositionLine (BasicObject from, BasicObject to) {
	  super (from, to);
	}
	
	@Override
	public void paintLine(Graphics g) {
	  super.paintLine(g);
      Graphics2D g2D = (Graphics2D) g;
	  g2D.draw (createPath2D ());
	}
	
	public Path2D createPath2D () {
      return new CompositionLinePath (_pairPorts[0], _pairPorts[1]);
	}
	
	public class CompositionLinePath extends Path2D.Double {
		private final double DIAMOND_SIDE = 15;
		private final double TRIANGLE_HEIGHT = DIAMOND_SIDE * 6/7;
		public CompositionLinePath (Point from, Point to) {
		  double fromX = from.getX ();
		  double fromY = from.getY ();
		  double toX = to.getX ();
		  double toY = to.getY ();
		  double totalLength = from.distance(to);
		  double lineLength = totalLength - TRIANGLE_HEIGHT;
			    
		  Point pointOfHeightOnLine = new Point ();
		  pointOfHeightOnLine.setLocation ((fromX*TRIANGLE_HEIGHT + toX*lineLength)/totalLength, (fromY*TRIANGLE_HEIGHT + toY*lineLength)/totalLength);
		
		  Point verticalVector = new Point ((int) (toY - fromY), (int) (fromX - toX));
		  verticalVector.setLocation ((toY - fromY)*DIAMOND_SIDE/(2*totalLength), (fromX - toX)*DIAMOND_SIDE/(2*totalLength));
	      
		  moveTo (fromX, fromY);
		  lineTo (pointOfHeightOnLine.getX () - (toX - pointOfHeightOnLine.getX ()), pointOfHeightOnLine.getY () - (toY - pointOfHeightOnLine.getY ()));
		  lineTo (pointOfHeightOnLine.getX () + verticalVector.getX (), pointOfHeightOnLine.getY () + verticalVector.getY ());
		  lineTo (toX, toY);
		  lineTo (pointOfHeightOnLine.getX () - verticalVector.getX (), pointOfHeightOnLine.getY () - verticalVector.getY ());
		  lineTo (pointOfHeightOnLine.getX () - (toX - pointOfHeightOnLine.getX ()), pointOfHeightOnLine.getY () - (toY - pointOfHeightOnLine.getY ()));
		  closePath ();
		}
	}
}