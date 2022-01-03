package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;

public class NameText extends AbstractUI {

    public NameText() {
        super(MENU_SELECT, 0, 0, 400, 60);
        setPosition(Gdx.graphics.getWidth() * 0.69f, Gdx.graphics.getHeight() * 0.3f);
        fontData = CARD_BIG_NAME;
        text = "";
        showImg = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(fontData != null) {
                renderLineLeft(sb, fontData, text, x, y, sWidth, sHeight);
            }
        }
    }
}
