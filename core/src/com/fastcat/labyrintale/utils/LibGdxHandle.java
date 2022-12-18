package com.fastcat.labyrintale.utils;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;

public final class LibGdxHandle {

    private static Lwjgl3Window currentWindow;

    public static Lwjgl3Window getCurrentWindow() {
        return currentWindow;
    }

    public static void setCurrentWindow(Lwjgl3Window currentWindow) {
        LibGdxHandle.currentWindow = currentWindow;
    }
}
