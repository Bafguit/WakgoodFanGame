package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class EffectPublicText extends AbstractUI {

    public EffectPublicText(Sprite s, float width, float height) {
        super(s, -1000, -1000, width, height);
        fontData = new FontData(MEDIUM, 19, false, true);
        text = "0";
        showImg = false;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(fontData != null) {
                if(showImg) img.draw(sb);
                renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
