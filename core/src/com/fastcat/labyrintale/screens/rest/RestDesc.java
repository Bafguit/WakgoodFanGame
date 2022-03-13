package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderColorLeft;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class RestDesc extends AbstractUI {

    public RestDesc(String d) {
        super(FileHandler.CHAR_SKILL_REWARD);
        text = d;
        showImg = false;
        fontData = new FontHandler.FontData(FontHandler.FontType.MEDIUM, 36, false);
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(fontData != null) {
                renderColorLeft(sb, fontData, text, x, y + sHeight / 2, sWidth);
            }
        }
    }
}
