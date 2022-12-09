package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CloseTempScreenButton extends AbstractUI {

    public CloseTempScreenButton(AbstractScreen screen) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.4f);
        fontData = BUTTON;
        text = "닫기";
        this.screen = screen;
    }

    @Override
    protected void onClick() {
        Labyrintale.removeTempScreen(screen);
    }
}
