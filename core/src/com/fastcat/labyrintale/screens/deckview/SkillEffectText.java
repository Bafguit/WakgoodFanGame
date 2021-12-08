package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.deckViewScreen;
import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCardLeft;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class SkillEffectText extends AbstractUI {

    public SkillEffectText() {
        super(CHAR_SELECT, 0, 0, 400, 180);
        setPosition(Gdx.graphics.getWidth() * 0.69f, Gdx.graphics.getHeight() * 0.25f - 45 * scale);
        fontData = CARD_BIG_DESC;
        text = "";
        showImg = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled && deckViewScreen.info.skill != null) {
            renderCardLeft(sb, deckViewScreen.info.skill, fontData, deckViewScreen.info.skill.desc, x, y, sWidth, sHeight);
        }
    }
}
