package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.ImageHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.ImageHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class EffectText extends AbstractUI {

    public EffectText() {
        super(CHAR_SELECT, 0, 0, 400, 180);
        setPosition(Gdx.graphics.getWidth() * 0.69f, Gdx.graphics.getHeight() * 0.275f - 45 * scale);
        fontData = CARD_BIG_DESC;
        text = "";
        showImg = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(fontData != null && battleScreen.skillInfo.skill != null) {
                renderCardLeft(sb, battleScreen.skillInfo.skill, fontData, text, x, y, sWidth, sHeight);
            }
        }
    }
}
