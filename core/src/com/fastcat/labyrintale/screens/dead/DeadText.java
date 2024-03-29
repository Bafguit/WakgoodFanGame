package com.fastcat.labyrintale.screens.dead;

import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_ORB;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class DeadText extends AbstractUI {

    public DeadText() {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 600, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 1008 * InputHandler.scale);
        fontData = CARD_BIG_ORB;
        text = "";
        showImg = false;
        overable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (fontData != null) {
                renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
