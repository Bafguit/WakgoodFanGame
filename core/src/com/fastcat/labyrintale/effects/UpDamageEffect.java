package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectPublicText;

import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class UpDamageEffect extends AbstractEffect {

    private final EffectPublicText text;

    public UpDamageEffect(float x, float y, int damage, Color color, boolean isNegative) {
        super(x, y, 1);
        text = new EffectPublicText(FileHandler.ui.get("MENU_SELECT"), 300, 60);
        text.fontData = new FontHandler.FontData(MEDIUM, 53, color);
        damage = Math.max(damage, 0);
        text.text = damage != 0 ? isNegative ? "-" + damage : "+" + damage : "0";
        text.setPosition(x - text.sWidth / 2, y - text.sHeight / 2);
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(duration != baseDuration) {
            if(text.fontData != null) {
                text.fontData.alpha -= Labyrintale.tick;
                if (text.fontData.alpha < 0) text.fontData.alpha = 0;
                text.y += Labyrintale.tick * 100;
            }
        }
        text.render(sb);
    }

    @Override
    public void dispose() {
        text.fontData.dispose();
    }
}
