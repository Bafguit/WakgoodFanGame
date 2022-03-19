package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCardLeft;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class UpEffectText extends AbstractUI {

    private AbstractSkill skill;

    public UpEffectText() {
        super(BORDER, 0, 0, 400, 180);
        setPosition(Gdx.graphics.getWidth() * 0.69f, Gdx.graphics.getHeight() * 0.25f - 45 * scale);
        fontData = CARD_BIG_DESC;
        text = "";
        showImg = false;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(screen instanceof DeckViewScreen) {
            DeckViewScreen s = ((DeckViewScreen) screen);
            if (enabled && s.upInfo.skill != null) {
                renderCardLeft(sb, s.upInfo.skill, fontData, s.upInfo.skill.desc, x, y, sWidth, sHeight);
            }
        }
    }
}
