package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class PreDeckPageButton extends AbstractUI {

    public PreDeckPageButton() {
        super(BACK);
        setPosition(Gdx.graphics.getWidth() * 0.18f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f - sHeight / 2);
        fontData = MAIN_MENU;
        text = "이전";
        showImg = false;
        disable();
    }

    @Override
    protected void updateButton() {
        if(!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        if(screen instanceof DeckViewScreen) ((DeckViewScreen)screen).adjustPage(-1);
    }
}
