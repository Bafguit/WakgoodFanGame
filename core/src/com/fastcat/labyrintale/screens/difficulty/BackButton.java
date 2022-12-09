package com.fastcat.labyrintale.screens.difficulty;

import static com.fastcat.labyrintale.Labyrintale.mainMenuScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class BackButton extends AbstractUI {

    public BackButton() {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
        fontData = BUTTON;
        text = "뒤로";
    }

    @Override
    protected void onClick() {
        Labyrintale.fadeOutAndChangeScreen(mainMenuScreen);
    }
}
