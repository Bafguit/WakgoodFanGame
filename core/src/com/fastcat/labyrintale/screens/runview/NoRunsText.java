package com.fastcat.labyrintale.screens.runview;

import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class NoRunsText extends AbstractUI {

    public NoRunsText() {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 2000, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 720 * InputHandler.scale - sHeight / 2);
        fontData = CARD_BORDER;
        text = "기록이 존재하지 않습니다.";
        showImg = false;
        overable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (fontData != null) {
                renderCenter(sb, fontData, text, x, y, sWidth, sHeight);
            }
        }
    }
}
