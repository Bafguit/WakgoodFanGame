package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.screens.map.MapScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FileHandler.NEXT;

public class NextButton extends AbstractUI {

    public NextButton() {
        super(NEXT);
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU.cpy();
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
        Labyrintale.advisorSelectScreen.backButton.onHide();
        Labyrintale.advisorSelectScreen.nextButton.onHide();
        Labyrintale.labyrinth = new AbstractLabyrinth();
        Labyrintale.mapScreen = new MapScreen();
        Labyrintale.game.setScreen(Labyrintale.mapScreen);
    }
}
