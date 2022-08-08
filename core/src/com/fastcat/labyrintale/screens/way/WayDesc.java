package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderColorCenter;

public class WayDesc extends AbstractUI {

    public WayDesc(String d) {
        super(FileHandler.getUi().get("WAY_SELECT"));
        text = d;
        showImg = false;
        fontData = FontHandler.WAY;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if (fontData != null) {
                renderColorCenter(sb, fontData, AbstractLabyrinth.bleak >= 60 ? "???" : text, x + sWidth * 0.1f, y + sHeight / 2, sWidth * 0.8f);
            }
        }
    }
}
