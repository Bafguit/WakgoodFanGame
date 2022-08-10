package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;


public class BgImg extends AbstractUI {

    public BgImg() {
        super(FileHandler.getBg().get("BG_BLACK"));
        setPosition(0, 0);
        img.setAlpha(0.75f);
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        img.draw(sb);
    }
}
