package BasicObjects;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;


public class UseCaseObject extends UMLObject {
	private Ellipse2D _ellipse;
	private String _name = "Use Case";
    private final Font _FONT = new Font ("Serif", Font.BOLD, 14);
	
	public UseCaseObject (int posX, int posY, int width, int height) {
	  super (posX, posY, width, height);
	
	  _ellipse = new Ellipse2D.Double (getX (), getY (), getWidth (), getHeight ());
	}

	public void paintObject (Graphics g) {
	  super.paintObject (g);
	  
	  Graphics2D g2D = (Graphics2D) g;
	  g2D.draw (_ellipse);
	  
      Point textPosition = findBestPositionOfText (g2D, _name);
	  
	  g2D.drawString(_name, (int) textPosition.getX (), (int) textPosition.getY ());
	}

	public void moveTo (int x, int y) {
	  super.moveTo (x, y);
	  _ellipse.setFrame (getX (), getY (), getWidth (), getHeight ());
	}
	
	private Point findBestPositionOfText (Graphics2D g2D, String text) {
	  FontMetrics fm = g2D.getFontMetrics (_FONT);
	  double textHeight = fm.getHeight ();
	  double textWidth = fm.stringWidth (_name);
	  double textX = getX () + (getWidth ()/2 - textWidth/2);
	  double textY = getY () + (getHeight ()/2 + textHeight/2);
	  return new Point ((int) textX, (int) textY);
    }
	
    public void setName (String name) {
      _name = name;
    }
}
