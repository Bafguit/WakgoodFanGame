package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class InputHandler {

    private static Array<ScaleUpdateListener> scaleUpdateListener = new Array<>();
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
        scale = 1.0f;
    }

    public void update() {
        int gx = Gdx.input.getX(), gy = Gdx.input.getY(), sw = Gdx.graphics.getWidth(), sh = Gdx.graphics.getHeight();
        float ts = scale;
        scale = (float) sw / 1920.0f;
        if(ts != scale) scaleUpdate(ts,  scale);
        isLeftClick = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
        isLeftClicking = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        isCursorInScreen = gx < sw && gx > 0 && gy < sh && gy > 0;
        mx = (int) (Math.max(Math.min(gx, sw), 0) / scale);
        my = (int) ((sh - Math.max(Math.min(gy, sh), 0)) / scale);
    }

    private void scaleUpdate(float pre, float cur) {
        for(Iterator iter = scaleUpdateListener.iterator(); iter.hasNext();) {
            ((ScaleUpdateListener)iter.next()).scaleUpdate(pre, cur);
        }
    }

    public static void addListener(ScaleUpdateListener listener) {
        if(listener != null) scaleUpdateListener.add(listener);
    }

    public interface ScaleUpdateListener {
        void scaleUpdate(float pre, float cur);
    }
}
