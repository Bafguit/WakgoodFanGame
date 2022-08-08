package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectPublicText;

public class TextEffect extends AbstractEffect {

    private final EffectPublicText text;
    private final BitmapFont f;

    public TextEffect(float x, float y, String s) {
        super(x, y, 0.5f);
        text = new EffectPublicText(FileHandler.getUi().get("MENU_SELECT"), 300, 60);
        text.fontData = FontHandler.CARD_BIG_DESC;
        text.text = s;
        text.setPosition(x - text.sWidth / 2, y - text.sHeight / 2);
        f = text.fontData.font;
    }

    @Override
    protected void updateEffect() {
        float m = baseDuration * 0.3f;
        if (duration <= m) {
            f.setColor(f.getColor().r, f.getColor().g, f.getColor().b, f.getColor().a - Labyrintale.tick / m);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        text.render(sb);
    }

    @Override
    public void dispose() {

    }
}
