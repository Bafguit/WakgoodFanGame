package com.fastcat.labyrintale.uis.slidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class SlideSideR extends AbstractUI {
    public SlideSideR() {
        super(FileHandler.ui.get("SLIDE_SR"));
        clickable = false;
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
