package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.screens.reward.RewardScreen.RewardScreenType.CHEST;
import static com.fastcat.labyrintale.screens.reward.RewardScreen.RewardScreenType.VICTORY;

public class RewardInfoText extends AbstractUI {

    public RewardInfoText() {
        super(MENU_SELECT, 0, 0, 600, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, Gdx.graphics.getHeight() * 0.4f - sHeight * 0.5f);
        fontData = CARD_BIG_DESC;
        text = "";
        showImg = false;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(fontData != null) {
                renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
