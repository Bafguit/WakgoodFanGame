package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class EffectText2 extends AbstractUI {

    public EffectText2() {
        super(CHAR_SELECT, 0, 0, 400, 180);
        setPosition(Gdx.graphics.getWidth() * 0.69f, Gdx.graphics.getHeight() * 0.25f - 45 * scale);
        fontData = CARD_BIG_DESC;
        text = "";
        showImg = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(fontData != null && battleScreen.statusInfo.status != null) {
                renderLineTopLeft(sb, fontData, text, x, y, sWidth, sHeight);
            }
        }
    }
}
