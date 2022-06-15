package com.fastcat.labyrintale.uis.slidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class SlideLine extends AbstractUI {
    public SlideLine(float width) {
        super(FileHandler.ui.get("SLIDE_L"), -1000, -1000, width - 12, 40);
        clickable = false;
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
