package com.mygdx.libgdx.guil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.libgdx.guil.Widget.Point;

/**
 * Manages all widgets.  The 'size' is completely arbitrary and you will base it on your world size
 * in your games.  The default value of this size can be set by you.
 * 
 * @author Algodal
 */
public class WidgetManager implements Disposable {
	private final static float minSize = 1;
	private static float defaultSize = 100f;
	public static void setDefaultSize(float ds) { defaultSize = (ds > minSize) ? ds : minSize; }
	
	public final float size;
	
	private final Stage stage;
	private final ScreenViewport vp_screen;
	
	private Viewport vp;
	
	private final Array<Widget> widgets;
	
	private int windowWidth, windowHeight;
	
	private final InputMultiplexer im;
	
	/** camera position **/
	public final Vector3 v3;
	
	/**
	 * @param size specify the size of this object.
	 */
	public WidgetManager(float size) {
		this.size = (size > defaultSize) ? size : defaultSize;
		
		vp_screen = new ScreenViewport();
		stage = new Stage(vp_screen);
		
		widgets = new Array<Widget>();
		
		setViewport(null);
		
		im = new InputMultiplexer(stage);
		
		v3 = new Vector3();
	}
	
	/**
	 * Uses the default size.
	 */
	public WidgetManager() {
		this(defaultSize);
	}
	
	/**
	 * @param processor additional processor you want to add to your game.
	 */
	public void appendProcessor(InputProcessor processor) {
		im.addProcessor(processor);
	}
	
	/**
	 * @param processor removes the processor you initially added.
	 */
	public void removeProcessor(InputProcessor processor) {
		if(im.getProcessors().contains(processor, true)) 
			im.removeProcessor(processor);
	}
	
	/**
	 * Clears all processors except the internal stage (of course).
	 */
	public void clearProcessors() {
		im.clear();
		im.addProcessor(stage);
	}
	
	/**
	 * Set all processors (including the internal stage) managed by this object to
	 * 'Gdx.input.setInputProcessor()'.  Well this blocks you from setting any other
	 * InputProcessor.  If want to add this object and other objects to the input processor
	 * use the other method, 'joinProcessor()'.
	 */
	public void setProcessor() {
		Gdx.input.setInputProcessor(im);
	}
	
	/** 
	 * @param im adds your internal stage (and other processors managed by this object) to this 
	 * input multiplexer.
	 */
	public void joinProcessor(InputMultiplexer im) {
		im.addProcessor(this.im);
	}
	
	/**
	 * @param vp sets this viewport as the currently used viewport.
	 * @return allows chaining.
	 */
	public WidgetManager setViewport(Viewport vp) {
		this.vp = (vp != null) ? vp : vp_screen;
		return this;
	}
	
	/**
	 * Call this in libGdx ApplicationListener's render method.
	 * @param delta Gdx.graphics.getDeltaTime() is recommended here
	 */
	public void render(float delta) {
		vp.update(windowWidth, windowHeight, true);
		render_size();
		render_position();
		render_stage(delta);
	}
	
	private void render_size() {
		for(Widget w : widgets) w.size(w.actor, w.pw * size, w.ph * size);
	}
	
	private void render_position() {
		for(Widget w : widgets) {
			render_position_points(w);
			
			final float x = w.px * size;
			final float y = w.py * size;
			final float width = w.pw * size;
			final float height = w.ph * size;
			
			final float left = x - (width / 2);
			final float bottom = y - (height / 2);
			
			w.position(w.actor, left, bottom);
		}
	}
	
	private void render_position_points(Widget w) {
		switch(w.point_x) {
			case Left : {
				final float width = w.pw * size;
				final float x = (-size / 2) + width / 2;
				w.px = x / size;
				break;
			}
			
			case Right : {
				final float width = w.pw * size;
				final float x = (size / 2) - width / 2;
				w.px = x / size;
				break;
			}
			
			default : break;
		}
		
		switch(w.point_y) {
			case Bottom : {
				final float height = w.ph * size;
				final float y = (-size / 2) + height / 2;
				w.py = y / size;
				break;
			}
			
			case Top : {
				final float height = w.ph * size;
				final float y = (size / 2) - height / 2;
				w.py = y / size;
				break;
			}
			
			default : break;
		}
		
		w.point_x = Point.Center;
		w.point_y = Point.Center;
	}
	
	private void render_stage(float delta) {
		stage.setViewport(vp);
		vp.getCamera().position.set(v3);
		stage.draw();
		stage.act(delta);
	}
	
	/**
	 * Call this method in libgdx ApplicationListener's resize()
	 * @param width libgdx's stuff
	 * @param height libgdx's stuff
	 */
	public void resize(int width, int height) {
		windowWidth = width;
		windowHeight = height;
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
	
	/**
	 * @param widget adds this widget to the management by this object.
	 */
	public void appendWidget(Widget widget) {
		widgets.add(widget);
		if(widget.actor != null) stage.addActor(widget.actor);
	}
	
	/**
	 * @param widget removes this widget from the management.
	 */
	public void removeWidget(Widget widget) {
		if(widgets.contains(widget, true)) {
			widgets.removeValue(widget, true);
			if(widget.actor != null) stage.getActors().removeValue(widget.actor, true);
		}
	}
	
	/**
	 * Removes all widgets from this management.
	 */
	public void clear() {
		widgets.clear();
		stage.clear();
	}
}





























