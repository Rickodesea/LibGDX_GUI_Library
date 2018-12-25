package com.mygdx.libgdx.guil.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.libgdx.guil.demo.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) Game.width;
		config.height = (int) Game.height;
		new LwjglApplication(new Game(), config);
	}
}
