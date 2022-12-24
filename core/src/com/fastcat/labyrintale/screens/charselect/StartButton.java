package com.fastcat.labyrintale.screens.charselect;

import static com.badlogic.gdx.graphics.Color.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

public class StartButton extends AbstractUI {

    public StartButton() {
        super(FileHandler.getUi().get("CHAR_START"));
        isPixmap = true;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!overable) sb.setColor(DARK_GRAY);
            else if(over) sb.setColor(WHITE);
            else sb.setColor(LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        SoundHandler.playSfx("CHANGE_DOOR");
        Labyrintale.fadeOutAndChangeScreen(new LoadingScreen(true), Labyrintale.FadeType.VERTICAL);
    }
}
