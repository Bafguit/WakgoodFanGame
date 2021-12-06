package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class InputHandler {

    public static boolean isLeftClick;
    public static boolean isLeftClicking;
    public static boolean isCursorInScreen;
    public static float scale;
    public static int mx;
    public static int my;

    public InputHandler() {
        isLeftClick = false;
        isLeftClicking = false;
        mx = 0;
        my = 0;
        scale = (float) Gdx.graphics.getWidth() / 1920.0f;
    }

    public void update() {
        int gx = Gdx.input.getX(), gy = Gdx.input.getY(), sw = Gdx.graphics.getWidth(), sh = Gdx.graphics.getHeight();
        scale = (float) sw / 1920.0f;
        isLeftClick = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
        isLeftClicking = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        isCursorInScreen = gx < sw && gx > 0 && gy < sh && gy > 0;
        mx = Math.max(Math.min(gx, sw), 0);
        my = sh - Math.max(Math.min(gy, sh), 0);
    }
}
