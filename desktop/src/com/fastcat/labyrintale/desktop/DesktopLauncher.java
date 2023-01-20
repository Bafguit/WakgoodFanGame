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

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setWindowIcon(Files.FileType.Internal, "img/icon.png");
        config.setTitle("Wakest Dungeon - " + BuildInfo.BUILD_VERSION);
        config.useVsync(true);
        config.setResizable(false);
        config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 20);

        new Lwjgl3Application(new Labyrintale(), config);
    }
}
