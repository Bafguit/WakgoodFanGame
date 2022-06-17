package com.fastcat.labyrintale.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fastcat.labyrintale.Labyrintale;

public class DesktopLauncher {
	public static void main (String[] arg) {
		try {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			new LwjglApplication(new Labyrintale(), config);
			config.samples = 3;
		} catch(Exception e) {
			new ErrorWindow(e).setVisible(true);
		}
	}
}
