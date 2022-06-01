package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.*;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;

public class InputHandler {

    public static boolean keypadMode;
    public static boolean keyInputMode;
    public static boolean isLeftClick;
    public static boolean isLeftClicking;
    public static boolean isCursorInScreen;
    public static boolean cancel;
    public static boolean map;
    public static boolean info;
    public static float scale;
    public static int mx;
    public static int my;

    public InputHandler() {
        isLeftClick = false;
        isLeftClicking = false;
        mx = 0;
        my = 0;
        scale = (float) Gdx.graphics.getWidth() / 2560.0f;
    }

    public void update() {
        int gx = Gdx.input.getX(), gy = Gdx.input.getY(), sw = Gdx.graphics.getWidth(), sh = Gdx.graphics.getHeight();
        scale = (float) sw / 2560.0f;
        isLeftClick = Gdx.input.isButtonJustPressed(Buttons.LEFT);
        isLeftClicking = Gdx.input.isButtonPressed(Buttons.LEFT);
        isCursorInScreen = gx < sw && gx > 0 && gy < sh && gy > 0;
        cancel = Gdx.input.isButtonJustPressed(Buttons.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE);
        map = Gdx.input.isKeyJustPressed(Keys.M);
        mx = Math.max(Math.min(gx, sw), 0);
        my = sh - Math.max(Math.min(gy, sh), 0);

        if(map && Labyrintale.mapScreen != null && Labyrintale.getCurScreen().type != AbstractScreen.ScreenType.MAP) {
            Labyrintale.mapScreen.view();
            map = false;
        }
    }
}
