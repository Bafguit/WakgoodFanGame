package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FileHandler.BACK;

public class BackButton extends AbstractUI {

    public BackButton() {
        super(BACK);
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
        for(int i = 0; i < Labyrintale.charSelectScreen.chars.length; i++) {
            Labyrintale.charSelectScreen.chars[i].removeChar();
        }
        Labyrintale.charSelectScreen.nextButton.disable();
        Labyrintale.charSelectScreen.backButton.onHide();
        Labyrintale.charSelectScreen.nextButton.onHide();
        Labyrintale.game.setScreen(Labyrintale.mainMenuScreen);
    }
}
