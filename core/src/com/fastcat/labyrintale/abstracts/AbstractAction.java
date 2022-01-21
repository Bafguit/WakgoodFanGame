package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

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
        if(target == AbstractSkill.SkillTarget.SELF) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(actor);
            this.target = temp;
        } else this.target = AbstractSkill.getTargets(target);
        this.duration = duration;
        baseDuration = this.duration;
    }

    public final void update() {
        if(!isDone) {
            if(actor != null && !actor.isAlive()) {
                isDone = true;
                return;
            } else if (duration <= 0) {
                isDone = true;
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
}
