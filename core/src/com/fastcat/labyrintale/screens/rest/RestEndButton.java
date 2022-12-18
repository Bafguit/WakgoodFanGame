package com.fastcat.labyrintale.screens.rest;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class RestEndButton extends AbstractUI {

    public RestEndButton() {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, 576 * InputHandler.scale);
        fontData = BUTTON;
        text = "계속";
    }

    @Override
    protected void onClick() {
        AbstractLabyrinth.endRoom();
    }
}
