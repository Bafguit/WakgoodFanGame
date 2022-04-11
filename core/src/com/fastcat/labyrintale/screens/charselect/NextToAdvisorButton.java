package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.advisorSelectScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class NextToAdvisorButton extends AbstractUI {

    public NextToAdvisorButton() {
        super(FileHandler.ui.get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU;
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
        Labyrintale.fadeOutAndChangeScreen(advisorSelectScreen, 0.5f);
    }
}
