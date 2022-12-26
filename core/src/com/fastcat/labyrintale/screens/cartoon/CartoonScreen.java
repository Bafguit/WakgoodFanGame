package com.fastcat.labyrintale.screens.cartoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.interfaces.AtCartoonEnd;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.io.File;

public class CartoonScreen extends AbstractScreen {

    private static final float CUT_TIME = 3;

    private final AtCartoonEnd parent;
    private final Sprite[] cut;
    private final float[] alpha;
    private final float w, h;

    private float timer = 0;
    private int index = 0;

    public boolean isDone;

    public CartoonScreen(String key, int size, AtCartoonEnd parent) {
        setBg(FileHandler.getUi().get("FADE"));
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        this.parent = parent;
        cut = new Sprite[size];
        alpha = new float[size];
        for(int i = 0; i < size;i ++) {
            Sprite s = new Sprite(FileHandler.getCartoonImg().get(key + "_" + (i + 1)));
            s.setBounds(0, 0, w, h);
            cut[i] = s;
            alpha[i] = 0;
        }
        isDone = false;
        cType = ControlPanel.ControlType.HIDE;
    }

    @Override
    public void update() {
        if(!isDone) {
            if(index < cut.length) {
                timer += Labyrintale.tick / CUT_TIME;
                if(alpha[index] < 1) {
                    alpha[index] += Labyrintale.tick;
                    if(alpha[index] >= 1) {
                        alpha[index] = 1;
                    }
                }
                if(timer >= 1) {
                    index++;
                    timer = 0;
                }
            } else {
                isDone = true;
                parent.cartoonEnded();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for(int i = 0; i < cut.length; i++) {
            cut[i].draw(sb, alpha[i]);
        }
    }

    @Override
    public void show() {

    }
}
