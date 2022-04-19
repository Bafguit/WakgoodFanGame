package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class GameStartButton extends AbstractUI {

    public GameStartButton() {
        super(FileHandler.ui.get("MENU_SELECT"));
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.5f);
        fontData = MAIN_MENU;
        text = "게임 시작";
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
        fadeOutAndChangeScreen(charSelectScreen, 1.0f);
    }
}
