import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

abstract class Mode{
    abstract void onPressed(MouseEvent e);
    abstract void onDragged(MouseEvent e);
    abstract void onReleased(MouseEvent e);
    abstract void onMoved(MouseEvent e);
    abstract Rectangle2D getBounding();
	
}
