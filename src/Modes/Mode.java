package Modes;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import BasicObjects.Composable;
import BasicObjects.Composite;

public abstract class Mode {
    public abstract void onPressed (MouseEvent e);
    public abstract void onDragged (MouseEvent e);
    public abstract void onReleased (MouseEvent e);
    public abstract void onMoved (MouseEvent e);
    public abstract void eidtName (String name);
    public void toGroup () {};
    public void toUnGroup () {};
    protected Composable findFrontFromComposables (Vector objectsVector) {
      Enumeration objects = objectsVector.elements ();
      Composable result = new Composite ();
       
      while (objects.hasMoreElements ()) {
      	Composable each = (Composable) objects.nextElement ();
      	
      	if (each.getDepth () < result.getDepth ()) {
          result = each;
    	}
      }
        
      return result;
    }
}
