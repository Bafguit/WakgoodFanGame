package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_ORB;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

public class SlotSelectText extends AbstractUI {

    public SlotSelectText() {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 1000, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, Gdx.graphics.getHeight() * 0.925f);
        fontData = CARD_BIG_ORB;
        text = "강화할 슬롯을 선택하세요";
        showImg = false;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (enabled) {
            if (fontData != null) {
                renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
