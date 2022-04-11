package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class NextButton extends AbstractUI {

    public NextButton() {
        super(FileHandler.ui.get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU;
        text = "출발";
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
        Labyrintale.fadeOutAndChangeScreen(new LoadingScreen(true));
    }
}
