package com.fastcat.labyrintale.screens.playerinfo;

import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class CloseInfoScreenButton extends AbstractUI {

    public CloseInfoScreenButton(PlayerInfoScreen screen) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, 1145 * InputHandler.scale);
        fontData = BUTTON;
        text = "닫기";
        this.screen = screen;
    }

    @Override
    protected void onClick() {
        playerInfoScreen.hide();
    }
}
