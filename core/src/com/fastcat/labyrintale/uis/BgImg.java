package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class BgImg extends AbstractUI {

    public BgImg(float alpha) {
        super(FileHandler.getBg().get("BG_BLACK"));
        setPosition(0, 0);
        img.setAlpha(alpha);
        overable = false;
    }

    public BgImg() {
        this(0.8f);
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        img.draw(sb);
    }
}
