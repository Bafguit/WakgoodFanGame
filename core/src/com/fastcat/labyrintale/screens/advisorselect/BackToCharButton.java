package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class BackToCharButton extends AbstractUI {

    public BackToCharButton() {
        super(FileHandler.ui.get("BACK"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU;
        text = "뒤로";
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
        Labyrintale.fadeOutAndChangeScreen(charSelectScreen, 0.5f);
    }
}
