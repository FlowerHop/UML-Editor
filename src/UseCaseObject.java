import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class UseCaseObject extends UMLObject {
	private Ellipse2D _ellipse;
	private String _name;
	
	public UseCaseObject (int posX, int posY, int width, int height) {
	  super (posX, posY, width, height);
	
	  _ellipse = new Ellipse2D.Double (getX (), getY (), getWidth (), getHeight ());
	}

	public void paintObject (Graphics g) {
	  Graphics2D g2D = (Graphics2D) g;
	  g2D.draw (_ellipse);
	}

	public void moveTo (int x, int y) {
	  super.moveTo (x, y);
	  _ellipse.setFrame (getX (), getY (), getWidth (), getHeight ());
	}
}
