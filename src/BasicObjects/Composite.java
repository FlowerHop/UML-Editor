package BasicObjects;

import java.util.Vector;

public interface Composite {
	public void toGroup (Composite... objs);
	public void toUnGroup ();
	public int getSize ();
}
