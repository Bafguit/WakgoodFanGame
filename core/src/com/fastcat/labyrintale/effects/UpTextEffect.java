package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectPublicText;

import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class UpTextEffect extends AbstractEffect {

    private final EffectPublicText text;

    public UpTextEffect(float x, float y, int damage, Color color, boolean isNegative) {
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
                text.fontData.font.getColor().a -= Gdx.graphics.getDeltaTime();
                if (text.fontData.font.getColor().a < 0) text.fontData.font.getColor().a = 0;
                text.y += Gdx.graphics.getDeltaTime() * 100;
                text.fontData.font.getData().setScale(text.fontData.font.getScaleY() - Gdx.graphics.getDeltaTime() / 2);
            }
        }
        text.render(sb);
    }

    @Override
    public void dispose() {
        text.dispose();
    }
}
