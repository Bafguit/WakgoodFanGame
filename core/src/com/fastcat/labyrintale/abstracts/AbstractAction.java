package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;

public abstract class AbstractAction implements Cloneable {

    protected static final float DUR_DEFAULT = 1.0f;
    protected static final float DUR_FAST = 0.5f;

    public String id;
    public ActionType type;
    public AbstractEntity actor;
    public AbstractEntity target;
    public AbstractEffect effect;
    public boolean isDone = false;
    public float baseDuration = DUR_DEFAULT;
    public float duration = DUR_DEFAULT;

    public AbstractAction(String id, ActionType type, AbstractEffect effect, float duration) {
        this.id = id;
        this.type = type;
        this.effect = effect;
        this.duration = duration;
        baseDuration = this.duration;
    }

    public final void update() {
        if(!isDone) {
            if(effect != null) {
                effect.update(duration);
            }
            updateAction();
            TickDuration();
            if (duration <= 0) {
                isDone = true;
            }
        }
    }

    protected abstract void updateAction();

    protected void TickDuration() {
        if (duration > 0) {
            duration -= Gdx.graphics.getDeltaTime();
        }
    }

    protected void setEntity(AbstractEntity actor, AbstractEntity target) {
        this.actor = actor;
        this.target = target;
    }

    public enum ActionType {
        NONE, BATTLE
    }
}
