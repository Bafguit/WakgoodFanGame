package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.interfaces.EventCallback;

import static com.fastcat.labyrintale.handlers.ActionHandler.listeners;

public abstract class AbstractAction implements Cloneable {

    protected static final float DUR_DEFAULT = 1.0f;
    protected static final float DUR_FAST = 0.5f;

    public AbstractEntity actor;
    public Array<AbstractEntity> target;
    public boolean isDone = false;
    public float baseDuration = DUR_DEFAULT;
    public float duration = DUR_DEFAULT;

    public AbstractAction(AbstractEntity actor, float duration) {
        this.actor = actor;
        this.duration = duration;
        baseDuration = this.duration;
    }

    public AbstractAction(AbstractEntity actor, AbstractSkill.SkillTarget target, float duration) {
        this.actor = actor;
        this.target = AbstractSkill.getTargets(actor, target);
        this.duration = duration;
        baseDuration = this.duration;
    }

    public final void update() {
        if(!isDone) {
            if(actor != null && !actor.isAlive()) {
                isDone = true;
                return;
            } else if (duration <= 0) isDone = true;
            onStart();
            updateAction();
            onComplete();
            TickDuration();
        }
    }

    protected abstract void updateAction();

    protected void TickDuration() {
        if (duration > 0) {
            duration -= Labyrintale.tick;
        }
    }

    protected void registerListener(EventCallback<AbstractAction> callback){
        listeners.add(callback);
    }

    private void onStart(){
        if(! listeners.isEmpty()){
            for (EventCallback<AbstractAction> listener : listeners) {
                listener.onStart(this);
            }
        }
    }

    private void onComplete(){
        if(! listeners.isEmpty()){
            for (EventCallback<AbstractAction> listener : listeners) {
                listener.onComplete(this);
            }
        }
    }

    @Override
    public AbstractAction clone() {
        try {
            return (AbstractAction) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
