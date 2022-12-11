package com.fastcat.labyrintale.screens.difficulty;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.mainMenuScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class BackButton extends AbstractUI {

    public BackButton() {
        super(FileHandler.getUi().get("BUTTON_S"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
        fontData = new FontData(MEDIUM, 53, false, false);
        text = "뒤로";
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && !over) fontData.color = Color.GRAY;
            else fontData.color = WHITE;

            renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        Labyrintale.fadeOutAndChangeScreen(mainMenuScreen);
    }
}
