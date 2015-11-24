package Modes;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public abstract class Mode {
    public abstract void onPressed (MouseEvent e);
    public abstract void onDragged (MouseEvent e);
    public abstract void onReleased (MouseEvent e);
    public abstract void onMoved (MouseEvent e);
    public abstract void eidtName (String name);
}
