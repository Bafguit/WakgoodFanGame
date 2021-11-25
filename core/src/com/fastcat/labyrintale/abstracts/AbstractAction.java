package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractAction implements Cloneable {

    protected static final float DUR_DEFAULT = 1.0f;
    protected static final float DUR_FAST = 0.5f;

    public AbstractEntity actor;
    public Array<AbstractEntity> target;
    public AbstractEffect effect;
    public boolean isDone = false;
    public float baseDuration = DUR_DEFAULT;
    public float duration = DUR_DEFAULT;

    public AbstractAction(AbstractEntity actor, Array<AbstractEntity> target, AbstractEffect effect, float duration) {
        this.actor = actor;
        this.target = target;
        this.effect = effect;
        this.duration = duration;
        baseDuration = this.duration;
        if(effect != null) {
            effect.duration = this.duration;
            effect.baseDuration = effect.duration;
        }
    }

    public final void update() {
        if(!isDone) {
            if(actor != null && actor.isDead) {
                isDone = true;
                return;
            } else if (duration <= 0) {
                isDone = true;
            }
            if(effect != null) {
                effect.update();
            }
            updateAction();
            TickDuration();
        }
    }

    protected abstract void updateAction();

    protected void TickDuration() {
        if (duration > 0) {
            duration -= Gdx.graphics.getDeltaTime();
        }
    }

    protected void setEntity(AbstractEntity actor, Array<AbstractEntity> target) {
        this.actor = actor;
        this.target = target;
    }
}
