package com.mygdx.libgdx.guil.demo;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ViewportSwitch {

	final Viewport[] vps;
	final String[] ss;
	
	int i;
	
	public final float w, h;
	
	public ViewportSwitch(float width, float height) {
		vps = new Viewport[] {
				new ScreenViewport(),
				new FitViewport(width, height),
				new FillViewport(width, height),
				new StretchViewport(width, height),
				new ExtendViewport(width, height)
		};
		
		ss = new String[] {
				"Screen Viewport",
				"Fit Viewport",
				"Fill Viewport",
				"Stretch Viewport",
				"Extend Viewport"
		};
		
		i = 0;
		
		w = width;
		h = height;
	}
	
	public void next() {
		i ++;
		i = (i >= vps.length) ? 0 : i;
	}
	
	public Viewport vp() { return vps[i]; }
	public String s() { return ss[i]; }
}













