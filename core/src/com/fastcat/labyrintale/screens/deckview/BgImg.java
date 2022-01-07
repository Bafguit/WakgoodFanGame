package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class BgImg extends AbstractUI {

    public BgImg() {
        super(FileHandler.BG_BLACK);
        setPosition(0, 0);
        img.setAlpha(0.7f);
    }

    @Override
    public void render(SpriteBatch sb) {
        img.draw(sb);
    }
}
