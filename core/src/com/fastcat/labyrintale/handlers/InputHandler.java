package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;

public final class InputHandler {

    public static boolean isLeftClick;
    public static boolean isLeftClicking;
    public static boolean isCursorInScreen;

    public static boolean cancel;
    public static boolean map;
    public static boolean info;

    public static float scale;
    public static int mx;
    public static int my;

    public static boolean textInputMode;
    public static Graphics.Monitor monitor;
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
        mx = 0;
        my = 0;
        scale = (float) Gdx.graphics.getWidth() / 2560.0f;

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean keyTyped(char c) {
                if (textInputMode) {
                    if (c == '\b') {
                        if (typedText.length() == 0)
                            backspaces++;
                        else
                            typedText = typedText.substring(0, typedText.length() - 1);
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
        if (instance == null)
            return (instance = new InputHandler());
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
        cancel = Gdx.input.isButtonJustPressed(Buttons.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE);

        if (textInputMode && (isLeftClick || Gdx.input.isKeyJustPressed(Keys.ENTER) || cancel)) textInputMode = false;

        if (!textInputMode) {
            map = Gdx.input.isKeyJustPressed(Keys.M);
        }

        AbstractScreen s = Labyrintale.getCurScreen();
        if (map && Labyrintale.mapScreen != null && s.type != AbstractScreen.ScreenType.MAP && s.type != AbstractScreen.ScreenType.SETTING) {
            MapScreen.view();
            map = false;
        }

        if(cancel && Labyrintale.settingScreen != null && Labyrintale.labyrinth != null && s.type != AbstractScreen.ScreenType.SETTING) {
            Labyrintale.addTempScreen(Labyrintale.settingScreen);
        }
    }
}
