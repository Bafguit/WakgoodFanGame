package com.fastcat.labyrintale.screens.itemselect;

import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_ORB;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class ItemSelectText extends AbstractUI {

    public ItemSelectText() {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 1000, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 1123 * InputHandler.scale);
        fontData = CARD_BIG_ORB;
        text = "아이템을 선택하세요";
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
