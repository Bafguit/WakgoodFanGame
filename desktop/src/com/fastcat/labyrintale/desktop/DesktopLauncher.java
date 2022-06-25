package com.fastcat.labyrintale.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fastcat.labyrintale.Labyrintale;

import java.lang.reflect.Field;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		LwjglApplication app = new LwjglApplication(new Labyrintale(), config);
		config.samples = 3;

		try {
			Field field = LwjglApplication.class.getDeclaredField("mainLoopThread");
			field.setAccessible(true);
			Thread mainLoopThread = (Thread) field.get(app);
			mainLoopThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					new ErrorWindow(e).setVisible(true);
				}
			});
		} catch(Exception e) {
			new ErrorWindow(e).setVisible(true);
		}
	}
}
