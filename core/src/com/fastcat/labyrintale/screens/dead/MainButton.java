package com.fastcat.labyrintale.screens.dead;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU_SHADOW;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class MainButton extends AbstractUI {

    public MainButton() {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 300, 50);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 288 * InputHandler.scale);
        fontData = MAIN_MENU_SHADOW;
        text = "처음으로";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        showImg = over;
    }

    @Override
    protected void onClick() {
        SoundHandler.fadeOutAll();
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
    }
}
