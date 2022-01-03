package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.NEXT;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class PassRewardButton extends AbstractUI {

    public PassRewardButton() {
        super(NEXT);
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.1f);
        fontData = MAIN_MENU;
        text = "넘기기";
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
        logger.log("Shutting Down...");
        Labyrintale.advisorSelectScreen.advisor.removeChar();
        Labyrintale.advisorSelectScreen.nextButton.disable();
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
    }
}
