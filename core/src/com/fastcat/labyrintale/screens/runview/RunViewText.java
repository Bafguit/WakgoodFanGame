package com.fastcat.labyrintale.screens.runview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.dead.DeadScreen;

import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

public class RunViewText extends AbstractUI {

    public RunViewText(DeadScreen.ScreenType type) {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 170, 60);
        setPosition(558 * InputHandler.scale, 285 * InputHandler.scale);
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
