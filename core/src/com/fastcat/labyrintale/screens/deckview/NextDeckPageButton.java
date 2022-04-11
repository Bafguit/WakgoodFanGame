package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class NextDeckPageButton extends AbstractUI {

    public NextDeckPageButton() {
        super(FileHandler.ui.get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.345f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f - sHeight / 2);
        fontData = MAIN_MENU;
        text = "다음";
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
        if(screen instanceof DeckViewScreen) ((DeckViewScreen)screen).adjustPage(1);
    }
}
