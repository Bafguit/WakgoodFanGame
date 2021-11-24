package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_NAME;
import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

public class NameText2 extends AbstractUI {

    public NameText2() {
        super(MENU_SELECT, 0, 0, 400, 60);
        setPosition(Gdx.graphics.getWidth() * 0.69f, Gdx.graphics.getHeight() * 0.25f);
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
