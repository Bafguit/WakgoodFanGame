package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FileHandler.bg;

public class BgImg extends AbstractUI {

    public BgImg() {
        super(bg.get("BG_BLACK"));
        setPosition(0, 0);
        img.setAlpha(0.75f);
    }

    @Override
    public void render(SpriteBatch sb) {
        img.draw(sb);
    }
}
