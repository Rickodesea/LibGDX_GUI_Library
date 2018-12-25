package com.mygdx.libgdx.guil;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Controls the internal actor.  Primary purpose is to control the positioning and the sizing.
 * Access the internal actor for more sophisticated things like setting a listener.
 * @author Algodal
 */
public class Widget {
	
	/**
	 * Only use this to change user value, like 'setText in Label'.  Widget  handles everything
	 * else.
	 */
	public final Actor actor;
	
	/**
	 * Percent values (between 0 and 1 inclusive (in the case of px and py it is -0.5 to +0.5)).  
	 * px and py are the position of the center and
	 * pw and ph are the width and the height.
	 */
	public float px, py, pw, ph;
	
	/**
	 * Support for left, right, bottom, top.
	 */
	protected Point point_x, point_y;
	protected float p_x, p_y;
	
	public Widget(Actor actor) {
		this.actor = actor;
		point_x = Point.Center;
		point_y = Point.Center;
	}
	
	/**
	 * Control the size.  Override this to do cool stuff.
	 * @param actor the internal actor.
	 * @param w generated width from pw and size.
	 * @param h generated width from pw and size.
	 */
	public void size(Actor actor, float w, float h) {
		actor.setSize(w, h);
	}
	
	/**
	 * Control position.  Override this to do cool stuff.
	 * @param actor the internal actor.
	 * @param x generated left from px and size.
	 * @param y generated bottom from py and size.
	 */
	public void position(Actor actor, float x, float y) {
		actor.setPosition(x, y);
	}
	
	//HELPER METHOD FOR WRITING SHORTER CODE (AND WRITING FASTER)
	
	public void setDimension(float px, float py, float pw, float ph) {
		setSize(pw, ph);
		setPosition(px, py);
	}
	
	public void setPosition(float px, float py) {
		this.px = px;
		this.py = py;
	}
	
	public void setSize(float pw, float ph) {
		this.pw = pw;
		this.ph = ph;
	}
	
	/**
	 * How far from the left.
	 * @param p 0 to 1 inclusive.
	 */
	public void left(float p) {
		point_x = Point.Left;
		this.p_x = p;
	}
	
	/**
	 * How far from the right.
	 * @param p 0 to 1 inclusive.
	 */
	public void right(float p) {
		point_x = Point.Right;
		this.p_x = p;
	}
	
	/**
	 * How far from the bottom.
	 * @param p 0 to 1 inclusive.
	 */
	public void bottom(float p) {
		point_y = Point.Bottom;
		this.p_y = p;
	}
	
	/**
	 * How far from the top.
	 * @param p 0 to 1 inclusive.
	 */
	public void top(float p) {
		point_y = Point.Top;
		this.p_y = p;
	}
	
	protected static enum Point {
		Left, Right, Bottom, Top, Center
	}
}






