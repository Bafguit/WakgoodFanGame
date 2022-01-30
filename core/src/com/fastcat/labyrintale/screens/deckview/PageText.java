package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class PageText extends AbstractUI {

    public PageText() {
        super(BORDER);
        setPosition(Gdx.graphics.getWidth() * 0.263f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f - sHeight / 2);
        fontData = MAIN_MENU;
        text = "";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if(screen instanceof DeckViewScreen) {
            DeckViewScreen s = ((DeckViewScreen) screen);
            text = (s.page + 1) + "/" + (s.max + 1);
        }
    }
}
