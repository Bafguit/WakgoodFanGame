package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class RestDesc extends AbstractUI {

    public RestDesc(String d) {
        super(FileHandler.ui.get("WAY_SELECT"));
        text = d;
        showImg = false;
        fontData = REST_DESC;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(fontData != null) {
                renderColorCenter(sb, fontData, text, x, y + sHeight / 2, sWidth);
            }
        }
    }
}
