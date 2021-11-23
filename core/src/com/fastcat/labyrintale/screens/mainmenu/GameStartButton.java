package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;

public class GameStartButton extends AbstractUI {

    public GameStartButton() {
        super(MENU_SELECT, 0, 0, 300, 50);
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.5f);
        fontData = MAIN_MENU.cpy();
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
        Labyrintale.mainMenuScreen.game.setScreen(Labyrintale.charSelectScreen);
    }
}
