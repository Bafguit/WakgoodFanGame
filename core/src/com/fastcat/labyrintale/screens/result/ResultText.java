package com.fastcat.labyrintale.screens.result;

import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.dead.DeadScreen;

public class ResultText extends AbstractUI {

    public ResultText(DeadScreen.ScreenType type) {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 1000, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 1260 * InputHandler.scale);
        fontData = CARD_BORDER;
        text = type == DeadScreen.ScreenType.WIN ? "승리" : "패배";
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
