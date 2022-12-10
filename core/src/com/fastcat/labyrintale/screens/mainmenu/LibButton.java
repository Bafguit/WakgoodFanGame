package com.fastcat.labyrintale.screens.mainmenu;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class LibButton extends AbstractUI {

    public LibButton() {
        super(FileHandler.getUi().get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.32f);
        fontData = new FontData(MEDIUM, 53, false);
        text = "도서관";
        showImg = false;
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
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.libScreen);
    }
}
