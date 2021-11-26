package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectText;

import static com.fastcat.labyrintale.handlers.EffectHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class HealEffect extends AbstractEffect {

    private int heal;

    private static final float LENGTH = 400;

    private boolean e1 = false;
    private boolean e2 = false;
    private boolean e3 = false;

    private boolean h1 = false;
    private boolean h2 = false;
    private boolean h3 = false;

    private float l1 = 0.0f;
    private float l2 = 0.0f;
    private float l3 = 0.0f;

    public HealEffect(float x, float y, int heal) {
        super(x, y, 0.5f);
        this.heal = heal;
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(!isDone) {
            if (duration == baseDuration && !e1) {
                e1 = true;
                EffectHandler.add(new TextEffect(x, y, Integer.toString(heal)));
            }
            else if (duration <= 0.4f && !e2) e2 = true;
            else if (duration <= 0.35 && !h1) h1 = true;
            else if (duration <= 0.3f && !e3) e3 = true;
            else if (duration <= 0.25 && !h2) h2 = true;
            else if (duration <= 0.2f && e1) e1 = false;
            else if (duration <= 0.15 && !h3) h3 = true;
            else if (duration <= 0.1f && e2) e2 = false;

            sb.end();
            shr.setColor(Color.CHARTREUSE);
            shr.begin(ShapeRenderer.ShapeType.Filled);

            if (e1) {
                if (!h1) l1 += Gdx.graphics.getDeltaTime() * LENGTH;
                else l1 -= Gdx.graphics.getDeltaTime() * LENGTH;

                shr.ellipse(x - 32.5f, y - l1 / 2, 5, l1);
            }
            if (e2) {
                if (!h2) l2 += Gdx.graphics.getDeltaTime() * LENGTH;
                else l2 -= Gdx.graphics.getDeltaTime() * LENGTH;

                shr.ellipse(x - 2.5f, y - l2 / 2, 5, l2);
            }
            if (e2) {
                if (!h3) l3 += Gdx.graphics.getDeltaTime() * LENGTH;
                else l3 -= Gdx.graphics.getDeltaTime() * LENGTH;

                shr.ellipse(x + 27.5f, y - l3 / 2, 5, l3);
            }
            shr.end();
            sb.begin();
        }
    }
}
