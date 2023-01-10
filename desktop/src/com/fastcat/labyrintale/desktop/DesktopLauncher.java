package com.fastcat.labyrintale.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowListener;
import com.fastcat.labyrintale.utils.BuildInfo;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.prototype.GameConfiguration;
import com.fastcat.labyrintale.prototype.providers.PlayerStatProvider;

import java.io.File;

public class DesktopLauncher {

    @Deprecated
    private static void loadConfiguration() {
        try {
            File parentFile = new File("settings");

            if (parentFile.exists() && parentFile.listFiles().length > 0) {
                GameConfiguration configuration = GameConfiguration.getInstance();
                configuration.setProviderClasses(PlayerStatProvider.class);
                configuration.loadAllProviders(parentFile);
                new ReloadWindow(parentFile).setVisible(true);
            }

        } catch (Exception e) {
            new ErrorWindow(e).setVisible(true);
        }
    }

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setWindowIcon(Files.FileType.Internal, "img/icon.png");
        config.setTitle("Wakest Dungeon - " + BuildInfo.BUILD_VERSION);
        config.useVsync(true);
        config.setResizable(false);
        //config.setInitialVisible(false);
        config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 20);


        SettingHandler.initialize(false);

        Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();

        if (SettingHandler.setting.screenMode == 0) {
            config.setWindowedMode(SettingHandler.setting.width, SettingHandler.setting.height);
        } else if (SettingHandler.setting.screenMode == 1) {
            config.setFullscreenMode(displayMode);
        } else {
            config.setDecorated(false);

            final int height, width;
            int w = displayMode.width, h = displayMode.height;
            float a = ((float) h) / ((float) w);

            if(a <= 0.5625f) {
                height = h;
                width = (int) (2560 * ((float) h / 1440.0f));
            } else {
                width = w;
                height = (int) (1440 * ((float) w / 2560.0f));
            }
            config.setWindowedMode(width, height);
            config.setWindowPosition(0, 0);
        }

        new Lwjgl3Application(new Labyrintale(), config);

/*
        try {
            Field field = Lwjgl3Application.class.getDeclaredField("loop");
            field.setAccessible(true);
            Thread mainLoopThread = (Thread) field.get(app);
            mainLoopThread.setUncaughtExceptionHandler((t, e) -> {
                Gdx.app.error("ERROR: ", "", e);
                new ErrorWindow(e).setVisible(true);
            });
        } catch (Exception e) {
            new ErrorWindow(e).setVisible(true);
        }*/
    }
}
