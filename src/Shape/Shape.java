package Shape;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public interface Shape {
	public void paintObject (Graphics g);
	public void move (int differenceX, int differenctY);
	public boolean contains (int x, int y);
	public boolean contains (Rectangle2D bounding);
    public void setSelect (boolean isSelect);
    public void setDepth (int depth);
    public int getDepth ();
    public double getX ();
    public double getY ();
}

