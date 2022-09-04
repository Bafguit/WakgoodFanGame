package com.fastcat.labyrintale.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.prototype.GameConfiguration;
import com.fastcat.labyrintale.prototype.providers.EntityStatProvider;

import java.io.File;
import java.lang.reflect.Field;

public class DesktopLauncher {
	private static void loadConfiguration(){
		try{
			File parentFile = new File("settings");

			if(parentFile.exists() && parentFile.listFiles().length > 0){
				GameConfiguration configuration = GameConfiguration.getInstance();
				configuration.setProviderClasses(EntityStatProvider.class);
				configuration.loadAllProviders(parentFile);
			}

		}catch (Exception e){
			new ErrorWindow(e).setVisible(true);
		}
	}
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		loadConfiguration();
		LwjglApplication app = new LwjglApplication(new Labyrintale(), config);
		config.samples = 3;

		try {
			Field field = LwjglApplication.class.getDeclaredField("mainLoopThread");
			field.setAccessible(true);
			Thread mainLoopThread = (Thread) field.get(app);
			mainLoopThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					Gdx.app.error("ERROR: ", "", e);
					new ErrorWindow(e).setVisible(true);
				}
			});
		} catch(Exception e) {
			new ErrorWindow(e).setVisible(true);
		}
	}
}
