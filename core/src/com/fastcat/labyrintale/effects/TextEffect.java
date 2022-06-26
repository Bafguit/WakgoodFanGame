package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectPublicText;

import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class TextEffect extends AbstractEffect {

    private EffectPublicText text;
    private BitmapFont f;

    public TextEffect(float x, float y, String s) {
        super(x, y, 0.5f);
        text = new EffectPublicText(FileHandler.ui.get("MENU_SELECT"), 300, 60);
        text.fontData = FontHandler.CARD_BIG_DESC;
        text.text = s;
        text.setPosition(x - text.sWidth / 2, y - text.sHeight / 2);
        f = text.fontData.font;
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(duration <= 0.15f) {
            f.setColor(f.getColor().r, f.getColor().g, f.getColor().b, f.getColor().a - Labyrintale.tick / 0.15f);
            System.out.println("FONT ALPHA: " + f.getColor().a);
        }
        text.render(sb);
    }

    @Override
    public void dispose() {

    }
}
