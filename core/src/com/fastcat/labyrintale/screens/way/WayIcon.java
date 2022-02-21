package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class WayIcon extends AbstractUI {

    public Sprite icon;

    public WayIcon(Sprite i) {
        super(FileHandler.BORDER);
        icon = i;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if(showImg) sb.draw(icon, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            if(fontData != null) {
                renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
