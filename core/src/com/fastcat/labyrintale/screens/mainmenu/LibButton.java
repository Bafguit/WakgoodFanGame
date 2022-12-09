package com.fastcat.labyrintale.screens.mainmenu;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU_SHADOW;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class LibButton extends AbstractUI {

    public LibButton() {
        super(FileHandler.getUi().get("MENU_SELECT"));
        setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.32f);
        fontData = MAIN_MENU_SHADOW;
        text = "도서관";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if (!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.libScreen);
    }
}
