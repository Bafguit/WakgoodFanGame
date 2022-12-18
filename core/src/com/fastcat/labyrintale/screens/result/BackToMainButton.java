package com.fastcat.labyrintale.screens.result;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class BackToMainButton extends AbstractUI {

    public BackToMainButton() {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, 72 * InputHandler.scale);
        fontData = BUTTON;
        text = "메인 메뉴";
    }

    @Override
    protected void onClick() {
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
    }
}
