package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderColorCenter;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class WayDesc extends AbstractUI {

    public WayDesc(String d) {
        super(FileHandler.ui.get("WAY_SELECT"));
        text = d;
        showImg = false;
        fontData = new FontHandler.FontData(FontHandler.FontType.MEDIUM, 48, false);
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(fontData != null) {
                renderColorCenter(sb, fontData, text, x, y + sHeight / 2, sWidth * 0.8f);
            }
        }
    }
}
