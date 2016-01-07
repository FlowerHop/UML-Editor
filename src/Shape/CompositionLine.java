package Shape;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;

import Shape.GeneralizationLine.GeneralizationLinePath;

public class CompositionLine extends ConnectionLine {
	public CompositionLine (Port from, Port to) {
	  super (from, to);
	}
	
	@Override
	public void paintObject (Graphics g) {
	  super.paintObject (g);
      Graphics2D g2D = (Graphics2D) g;
	  g2D.draw (createPath2D ());
	}
	
	public Path2D createPath2D () {
      return new CompositionLinePath (_head, _tail);
	}
	
	public class CompositionLinePath extends Path2D.Double {
		private final double DIAMOND_SIDE = 15;
		private final double TRIANGLE_HEIGHT = DIAMOND_SIDE * 6/7;
		public CompositionLinePath (Port from, Port to) {
		  double fromX = from.getRelativeX ();
		  double fromY = from.getRelativeY ();
		  double toX = to.getRelativeX ();
		  double toY = to.getRelativeY ();
		  double totalLength = from.distanceTo(to);
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