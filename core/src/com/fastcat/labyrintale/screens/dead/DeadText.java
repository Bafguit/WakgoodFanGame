package com.fastcat.labyrintale.screens.dead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.MENU_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.LOGO;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

public class DeadText extends AbstractUI {

    public DeadText() {
        super(MENU_SELECT, 0, 0, 600, 60);
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, Gdx.graphics.getHeight() * 0.7f);
        fontData = LOGO;
        text = "";
        showImg = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(fontData != null) {
                renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
