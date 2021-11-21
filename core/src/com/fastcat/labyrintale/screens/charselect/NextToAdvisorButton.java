package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.ImageHandler.NEXT;

public class NextToAdvisorButton extends AbstractUI {

    public NextToAdvisorButton() {
        super(NEXT);
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU.cpy();
        text = "다음";
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
        Labyrintale.charSelectScreen.backButton.onHide();
        Labyrintale.charSelectScreen.nextButton.onHide();
        Labyrintale.game.setScreen(Labyrintale.advisorSelectScreen);
    }
}
