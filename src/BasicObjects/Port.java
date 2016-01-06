package BasicObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Port {
	private final double WIDTH = 6;
	private final double HEIGHT = 6;
	
	private double _rx;
	private double _ry;
	
	public Port (double rx, double ry) {
	  _rx = rx;
	  _ry = ry;
	}
	
	public double getRelativeX () {
	  return _rx;
	}
	
	public double getRelativeY () {
	  return _ry;
	}
	
	public double getPortSide () {
	  return WIDTH;
	}
	
	public double distanceTo (Port to) {
	  return Math.sqrt(Math.pow(to._rx - _rx, 2) + Math.pow(to._ry - _ry, 2));
	}
	
	public void move (double x, double y) {
	  _rx = _rx + x;
	  _ry = _ry + y;
	}
}
