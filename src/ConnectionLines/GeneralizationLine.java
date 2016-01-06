package ConnectionLines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import BasicObjects.BasicObject;
import BasicObjects.Port;

public class GeneralizationLine extends ConnectionLine {
	
	public GeneralizationLine (Port from, Port to) {
	  super (from, to);
	}

	@Override
	public void paintLine(Graphics g) {
	  super.paintLine(g);
      Graphics2D g2D = (Graphics2D) g;
	  g2D.draw (createPath2D ());
	}
	
	public Path2D createPath2D () {
      return new GeneralizationLinePath (_head, _tail);
	}
	
	public class GeneralizationLinePath extends Path2D.Double {
		private final double TRIANGLE_SIDE = 15;
		private final double TRIANGLE_HEIGHT = TRIANGLE_SIDE * Math.pow(3, 1/2)/2;
		public GeneralizationLinePath (Port from, Port to) {
		  double fromX = from.getRelativeX ();
		  double fromY = from.getRelativeY ();
		  double toX = to.getRelativeX ();
		  double toY = to.getRelativeY ();
		  double totalLength = from.distanceTo (to);
		  double lineLength = totalLength - TRIANGLE_HEIGHT;
			    
		  Point pointOfHeightOnLine = new Point ();
		  pointOfHeightOnLine.setLocation ((fromX*TRIANGLE_HEIGHT + toX*lineLength)/totalLength, (fromY*TRIANGLE_HEIGHT + toY*lineLength)/totalLength);
			
		  Point verticalVector = new Point ((int) (toY - fromY), (int) (fromX - toX));
		  verticalVector.setLocation ((toY - fromY)*TRIANGLE_SIDE/(2*totalLength), (fromX - toX)*TRIANGLE_SIDE/(2*totalLength));
	      
		  moveTo (fromX, fromY);
		  lineTo (pointOfHeightOnLine.getX (), pointOfHeightOnLine.getY ());
		  lineTo (pointOfHeightOnLine.getX () + verticalVector.getX (), pointOfHeightOnLine.getY () + verticalVector.getY ());
		  lineTo (toX, toY);
		  lineTo (pointOfHeightOnLine.getX () - verticalVector.getX (), pointOfHeightOnLine.getY () - verticalVector.getY ());
		  lineTo (pointOfHeightOnLine.getX (), pointOfHeightOnLine.getY ());
		  closePath ();
		}
	}
}
