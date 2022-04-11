package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_NAME;
import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

public class UpNameText extends AbstractUI {

    public UpNameText() {
        super(FileHandler.ui.get("MENU_SELECT"), 0, 0, 400, 60);
        setPosition(Gdx.graphics.getWidth() * 0.69f, Gdx.graphics.getHeight() * 0.25f);
        fontData = CARD_BIG_NAME;
        text = "";
        showImg = false;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(screen instanceof DeckViewScreen) {
            DeckViewScreen s = ((DeckViewScreen) screen);
            if (enabled && fontData != null && s.upInfo.skill != null) {
                renderLineLeft(sb, fontData, s.upInfo.skill.name, x, y, sWidth, sHeight);
            }
        }
    }
}
