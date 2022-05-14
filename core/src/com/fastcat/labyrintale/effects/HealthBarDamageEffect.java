package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.handlers.EffectHandler;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class HealthBarDamageEffect extends AbstractEffect {

    private float alpha = 0;
    private final AbstractEntity entity;
    public boolean cont = true;

    public HealthBarDamageEffect(AbstractEntity e) {
        super(0, 0, 0.2f);
        entity = e;
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(cont) {
            if (duration == baseDuration) {
                if (entity.hbEffect != null && entity.hbEffect != this) {
                    entity.hbEffect.isDone = true;
                    entity.hbEffect.cont = false;
                }
                entity.hbEffect = this;
                entity.animColor.set(1, 0, 0, 1);

            } else {
                alpha = Math.min(alpha + Gdx.graphics.getDeltaTime() * 5, 1);
                entity.animColor.set(1, alpha, alpha, 1);
            }
            if(isDone) {
                entity.animColor.set(1, 1, 1, 1);
                entity.hbEffect = null;
            }
        }
    }
}
