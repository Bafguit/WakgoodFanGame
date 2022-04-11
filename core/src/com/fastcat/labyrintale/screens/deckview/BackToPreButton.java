package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class BackToPreButton extends AbstractUI {

    public BackToPreButton() {
        super(FileHandler.ui.get("BACK"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU;
        text = "닫기";
        showImg = false;
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
        if(screen != null) Labyrintale.removeTempScreen(screen);
    }
}
