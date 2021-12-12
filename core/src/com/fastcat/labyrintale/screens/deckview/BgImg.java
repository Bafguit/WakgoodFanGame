package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FileHandler.BACK;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class BgImg extends AbstractUI {

    public BgImg() {
        super(FileHandler.BG_TRANS);
        setPosition(0, 0);
        img.setAlpha(0.7f);
    }

    @Override
    public void render(SpriteBatch sb) {
        img.draw(sb);
    }
}
