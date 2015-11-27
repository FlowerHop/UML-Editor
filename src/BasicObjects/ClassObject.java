package BasicObjects;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class ClassObject extends UMLObject {
	private final int LINE_NUMBER = 2;
    
    private Rectangle2D _rect;
    private Line2D[] _lines;
    
    private String _name = "Class";
    private final Font _FONT = new Font ("Serif", Font.BOLD, 14);
    
    public ClassObject (int posX, int posY, int width, int height) {
      super (posX, posY, width, height);
	  _lines = new Line2D[LINE_NUMBER];
	  _rect = new Rectangle2D.Double (getX (), getY (), getWidth (), getHeight ());
	  
	  for (int i = 0; i < _lines.length; i++) {
		_lines[i] = new Line2D.Double (getX (), getY () + ((double) getHeight ())*((double) (i + 1)/(LINE_NUMBER + 1)), 
				getX () + getWidth (), getY () + ((double) getHeight ())*((double) (i + 1)/(LINE_NUMBER + 1)));	
	  }
	}
    
	public void paintObject (Graphics g) {
	  super.paintObject (g);
	  Graphics2D g2D = (Graphics2D) g;
	  g2D.draw (_rect);
	  for (int i = 0; i < _lines.length; i++) {
		g2D.draw (_lines[i]);
	  }
	  g2D.setFont (_FONT);
	  
	  Point textPosition = findBestPositionOfText (g2D, _name);
	  
	  g2D.drawString(_name, (int) textPosition.getX (), (int) textPosition.getY ());
	  
	}
	
	public void moveTo (int x, int y) {
		super.moveTo (x, y);
		
		for (int i = 0; i < _lines.length; i++) {
		  _lines[i] = new Line2D.Double (getX (), getY () + ((double) getHeight ())*((double) (i + 1)/(LINE_NUMBER + 1)), 
					getX () + getWidth (), getY () + ((double) getHeight ())*((double) (i + 1)/(LINE_NUMBER + 1)));	
		}
		_rect.setFrame (getX (), getY (), getWidth (), getHeight ());
	}

    private Point findBestPositionOfText (Graphics2D g2D, String text) {
      FontMetrics fm = g2D.getFontMetrics (_FONT);
  	  double textHeight = fm.getHeight ();
  	  double textWidth = fm.stringWidth (_name);
  	  double textX = getX () + (getWidth ()/2 - textWidth/2);
  	  double textY = getY () + (getHeight ()/(LINE_NUMBER + 1))/2 + textHeight/2;
  	  return new Point ((int) textX, (int) textY);
    }
    
    public void setName (String name) {
      _name = name;
    }
}

