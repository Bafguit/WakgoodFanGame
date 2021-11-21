package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.ImageHandler.BACK;

public class BackToCharButton extends AbstractUI {

    public BackToCharButton() {
        super(BACK);
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU.cpy();
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
        Labyrintale.advisorSelectScreen.advisor.removeChar();
        Labyrintale.advisorSelectScreen.nextButton.disable();
        Labyrintale.advisorSelectScreen.backButton.onHide();
        Labyrintale.advisorSelectScreen.nextButton.onHide();
        Labyrintale.game.setScreen(Labyrintale.charSelectScreen);
    }
}