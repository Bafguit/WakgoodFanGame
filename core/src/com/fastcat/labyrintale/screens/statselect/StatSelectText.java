package com.fastcat.labyrintale.screens.statselect;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class StatSelectText extends AbstractUI {

    public StatSelectText() {
        super(FileHandler.getUi().get("MENU_SELECT"), 0, 0, 1000, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, Gdx.graphics.getHeight() * 0.9f);
        fontData = CARD_BIG_ORB;
        text = "강화할 능력치를 선택하세요";
        showImg = false;
        overable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (fontData != null) {
                renderCenter(sb, fontData, text, x, y + sHeight * 0.5f, sWidth, sHeight);
                renderColorCenter(
                        sb,
                        CARD_BIG_NAME,
                        "보유한 능력치 포인트: &y<" + AbstractLabyrinth.sp + ">",
                        x,
                        y - sHeight * 0.7f,
                        sWidth);
            }
        }
    }
}
