package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractEffect {

    public float baseDuration;
    public float duration;
    public float x, y;
    public boolean isDone;

    public AbstractEffect(float x, float y, float duration) {
        this.x = x;
        this.y = y;
        this.duration = duration;
        this.baseDuration = this.duration;
    }

    public void render(SpriteBatch sb) {
        if(!isDone) {
            if (duration <= 0) {
                isDone = true;
            }
            renderEffect(sb);
            TickDuration();
        }
    }

    protected void TickDuration() {
        if (duration > 0) {
            duration -= Gdx.graphics.getDeltaTime();
        }
    }

    protected abstract void renderEffect(SpriteBatch sb);
}
