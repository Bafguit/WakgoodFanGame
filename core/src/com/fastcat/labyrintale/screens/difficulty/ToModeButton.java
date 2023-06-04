package com.fastcat.labyrintale.screens.difficulty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class ToModeButton extends AbstractUI {

    public ToModeButton() {
        super(FileHandler.getUi().get("RIGHT"));
        setScale(2);
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        SoundHandler.playSfx("CHANGE");
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.modeScreen, Labyrintale.FadeType.HORIZONTAL, 0.5f);
    }
}
