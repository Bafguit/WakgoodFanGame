package com.fastcat.labyrintale.screens.runview;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class BackToMainRunButton extends AbstractUI {

    public BackToMainRunButton() {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
        fontData = BUTTON;
        text = "뒤로";
    }

    @Override
    protected void onClick() {
        SoundHandler.playSfx("CHANGE");
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.libScreen, Labyrintale.FadeType.VERTICAL, 0.3f);
    }
}
