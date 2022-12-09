package com.fastcat.labyrintale.screens.reward;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class RewardText extends AbstractUI {

    public RewardText() {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 600, 60);
        setPosition(Gdx.graphics.getWidth() * 0.75f - sWidth / 2, Gdx.graphics.getHeight() * 0.75f);
        fontData = MAIN_MENU_SHADOW;
        text = "보상";
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
