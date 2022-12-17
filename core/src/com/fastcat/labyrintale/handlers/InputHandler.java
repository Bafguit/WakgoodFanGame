package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Rectangle;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoScreen;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;

public final class InputHandler {

    public static boolean isLeftClick;
    public static boolean isLeftClicking;
    public static boolean isCursorInScreen;
    public static boolean isDesktop;

    public static boolean cancel;
    public static boolean map;
    public static boolean info;
    public static boolean sc;

    public static float scale;
    public static int mx;
    public static int my;

    public static Rectangle cursor;

    public static boolean textInputMode;
    private static String typedText = "";
    private static int backspaces = 0;
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static InputHandler instance;

    private InputHandler() {
        isLeftClick = false;
        isLeftClicking = false;
        isDesktop = Gdx.app.getType() == Application.ApplicationType.Desktop;
        mx = 0;
        my = 0;
        cursor = new Rectangle(0, 0, 1, 1);
        scale = (float) Gdx.graphics.getWidth() / 2560.0f;

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean keyTyped(char c) {
                if (textInputMode) {
                    if (c == '\b') {
                        if (typedText.length() == 0) backspaces++;
                        else typedText = typedText.substring(0, typedText.length() - 1);
                    } else if ((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                        typedText += c;
                    } else if (c >= 'a' && c <= 'z') {
                        typedText += (char) (c - 32);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static InputHandler getInstance() {
        if (instance == null) return (instance = new InputHandler());
        return instance;
    }

    public static void setTextInputMode(boolean textInputMode) {
        InputHandler.textInputMode = textInputMode;
    }

    public static String getTypedText(String formerText) {
        StringBuilder sb = new StringBuilder();
        sb.append(formerText);
        sb.setLength(Math.max(formerText.length() - backspaces, 0));
        sb.append(typedText);
        typedText = "";
        backspaces = 0;
        return sb.toString();
    }

    public void update() {
        int gx = Gdx.input.getX(), gy = Gdx.input.getY(), sw = Gdx.graphics.getWidth(), sh = Gdx.graphics.getHeight();
        scale = (float) sw / 2560.0f;
        isLeftClick = Gdx.input.isButtonJustPressed(Buttons.LEFT);
        isLeftClicking = Gdx.input.isButtonPressed(Buttons.LEFT);
        isCursorInScreen = gx < sw && gx > 0 && gy < sh && gy > 0;

        mx = Math.max(Math.min(gx, sw), 0);
        my = sh - Math.max(Math.min(gy, sh), 0);
        cursor.setPosition(mx, my);
        cancel = Gdx.input.isButtonJustPressed(Buttons.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE);

        if (textInputMode && (isLeftClick || Gdx.input.isKeyJustPressed(Keys.ENTER) || cancel)) textInputMode = false;

        if (!textInputMode) {
            map = Gdx.input.isKeyJustPressed(Keys.M);
            info = Gdx.input.isKeyJustPressed(Keys.I);
            sc = Gdx.input.isKeyJustPressed(Keys.PRINT_SCREEN);
        }

        if (map && Labyrintale.mapScreen != null && !Labyrintale.mapScreen.showing && !Labyrintale.setting) {
            MapScreen.view();
            map = false;
        }
        if (info
                && Labyrintale.playerInfoScreen != null
                && !Labyrintale.playerInfoScreen.showing
                && !Labyrintale.setting) {
            PlayerInfoScreen.view();
            info = false;
        }

        if (sc) {
            Pixmap pixmap = Pixmap.createFromFrameBuffer(
                    0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
            ByteBuffer pixels = pixmap.getPixels();

            // This loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
            int size = Gdx.graphics.getBackBufferWidth() * Gdx.graphics.getBackBufferHeight() * 4;
            for (int i = 3; i < size; i += 4) {
                pixels.put(i, (byte) 255);
            }

            PixmapIO.writePNG(Gdx.files.local("screen.png"), pixmap, Deflater.DEFAULT_COMPRESSION, true);
            pixmap.dispose();
        }

        if (cancel && !Labyrintale.fading) {
            if (Labyrintale.labyrinth != null) {
                if (Labyrintale.settingScreen != null && !Labyrintale.settingScreen.anim) {
                    if (!Labyrintale.setting) {
                        if (Labyrintale.mapScreen != null
                                && !Labyrintale.mapScreen.showing
                                && Labyrintale.playerInfoScreen != null
                                && !Labyrintale.playerInfoScreen.showing) {
                            Labyrintale.openSetting();
                        }
                    } else {
                        Labyrintale.closeSetting();
                    }
                }
            }
        }
    }
}
