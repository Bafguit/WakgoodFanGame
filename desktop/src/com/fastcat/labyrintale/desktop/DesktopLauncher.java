package com.fastcat.labyrintale.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.fastcat.labyrintale.Labyrintale;

public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setWindowIcon(Files.FileType.Internal, "img/icon.png");
        config.setTitle("Wakest Dungeon");
        config.useVsync(true);
        config.setResizable(false);
        config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 20);

        new Lwjgl3Application(new Labyrintale(), config);
    }
}
