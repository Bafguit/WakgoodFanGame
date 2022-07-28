package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;

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

    public final void update() {
        if (duration <= 0) {
            isDone = true;
            duration = 0;
        }
        updateEffect();
        TickDuration();
    }

    protected void TickDuration() {
        if (duration > 0) {
            duration -= Labyrintale.tick;
        }
    }

    protected abstract void updateEffect();

    public abstract void render(SpriteBatch sb);

    public void onRemove() {

    }

    public void dispose() {

    }
}
