package com.mygdx.libgdx.guil.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.libgdx.guil.Widget;
import com.mygdx.libgdx.guil.WidgetManager;

public class Game extends ApplicationAdapter {
	
	public final static float width = 320;
	public final static float height = 16 * width / 9; 
	
	WidgetManager wm;
	Widget widget;
	Widget textbutton;
	Widget label, image, slider;
	Widget textfield;
	
	ViewportSwitch vps;
	
	@Override
	public void create () {
		wm = new WidgetManager(width);
		
		final Skin skin = new Skin(Gdx.files.internal("skin/default/uiskin.json"));
		
		widget = new Widget(new Button(skin));
		widget.pw = 0.2f;
		widget.ph = 0.1f;
		widget.bottom(0);
		widget.left(0);
		
		textbutton = new Widget(new TextButton("NEXT", skin));
		textbutton.pw = 0.3f;
		textbutton.ph = 0.15f;
		textbutton.py = 0.2f;
		
		textbutton.actor.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				vps.next();
				wm.setViewport(vps.vp());
			}
		});
		
		label = new Widget(new Label("GUIL demo", skin));
		label.setSize(0.3f, 0.1f);
		label.left(0);
		label.top(0);
		
		image = new Widget(new Image(new Texture("badlogic.jpg")));
		image.right(0);
		image.bottom(0);
		image.setSize(0.2f, 0.2f);
		
		slider = new Widget(new Slider(0, 10, 0.1f, false, skin));
		slider.setDimension(0, 0, 1, 0.1f);
		
		textfield = new Widget(new TextField("Enter text here...", skin));
		textfield.setPosition(-0.15f, -0.25f);
		textfield.setSize(0.5f, 0.1f);
		
		wm.appendWidget(widget);
		wm.appendWidget(textbutton);
		wm.appendWidget(label);
		wm.appendWidget(image);
		wm.appendWidget(slider);
		wm.appendWidget(textfield);
		
		wm.setProcessor();
		
		vps = new ViewportSwitch(width, height);
		
		wm.setViewport(vps.vp());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		wm.render(Gdx.graphics.getDeltaTime());
		
		final Label l = (Label)label.actor;
		l.setText(vps.s()+ " " + vps.w + ", " + vps.h);
	}
	
	@Override
	public void resize(int width, int height) {
		wm.resize(width, height);
	}
	
	@Override
	public void dispose () {
		wm.dispose();
	}
}
