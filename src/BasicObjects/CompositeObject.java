package BasicObjects;

import java.util.Vector;

public class CompositeObject {
	private Vector _umlComposites;
	
	public CompositeObject () {
	  _umlComposites = new Vector ();
	}
	
	public void toGroup (CompositeObject... objs) {
	  for (CompositeObject obj : objs) {
		_umlComposites.add (obj);
	  }
	}
	
	public int getDepth () {
	  return 0;
	}
}
