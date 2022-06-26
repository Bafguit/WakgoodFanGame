package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;

import java.io.Serializable;

public abstract class AbstractEffect implements Disposable {

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
        if (duration <= 0) {
            isDone = true;
        }
        renderEffect(sb);
        TickDuration();
        if(isDone) dispose();
    }

    protected void TickDuration() {
        if (duration > 0) {
            duration -= Labyrintale.tick;
        }
    }

    protected abstract void renderEffect(SpriteBatch sb);

    public void onRemove() {
        dispose();
    }

    public void dispose() {

    }
}
