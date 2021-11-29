package com.fastcat.labyrintale.screens.dead;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class MainButton extends AbstractUI {

    public MainButton() {
        super(MENU_SELECT, 0, 0, 300, 50);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, Gdx.graphics.getHeight() * 0.2f);
        fontData = MAIN_MENU.cpy();
        text = "처음으로";
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
        Labyrintale.game.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
    }
}
