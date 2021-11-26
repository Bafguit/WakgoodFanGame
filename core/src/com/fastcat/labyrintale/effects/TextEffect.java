package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectText;

import static com.fastcat.labyrintale.handlers.EffectHandler.shr;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class TextEffect extends AbstractEffect {

    private EffectText text = new EffectText();

    public TextEffect(float x, float y, String s) {
        super(x, y, 0.5f);
        text.fontData = new FontHandler.FontData(MEDIUM, 24, true);
        text.text = s;
        text.setPosition(x - text.sWidth / 2, y - text.sHeight / 2);
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(duration <= 0.15f) {
            text.fontData.font.getColor().a -= Gdx.graphics.getDeltaTime() / 0.15f;
        }
        text.render(sb);
    }
}
