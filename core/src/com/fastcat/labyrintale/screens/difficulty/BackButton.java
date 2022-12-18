package com.fastcat.labyrintale.screens.difficulty;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontData;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class BackButton extends AbstractUI {

    private final Color fColor = Color.GRAY.cpy();
    private final float aa = fColor.r;
    private float a = fColor.r;

    public BackButton() {
        super(FileHandler.getUi().get("BUTTON_S"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, 1296 * InputHandler.scale);
        fontData = new FontData(MEDIUM, 53, false, false);
        text = "뒤로";
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && !over) {
                a -= Labyrintale.tick * 4;
                if (a <= aa) a = aa;
            } else {
                a += Labyrintale.tick * 4;
                if (a >= 1) a = 1;
            }

            fColor.set(a, a, a, 1);
            fontData.color = fColor;

            renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    public void show() {
        a = aa;
    }

    @Override
    protected void onClick() {
        SoundHandler.playSfx("CHANGE");
        fadeOutAndChangeScreen(mainMenuScreen, FadeType.VERTICAL, 0.5f);
    }
}
