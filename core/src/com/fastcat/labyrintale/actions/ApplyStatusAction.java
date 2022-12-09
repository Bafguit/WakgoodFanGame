package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class ApplyStatusAction extends AbstractAction {

    private final AbstractStatus status;

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, AbstractEntity target, boolean fast) {
        super(actor, fast ? 0.25f : DUR_DEFAULT);
        this.status = status;
        Array<AbstractEntity> e = new Array<>();
        e.add(target);
        this.target = e;
    }

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, Array<AbstractEntity> target, boolean fast) {
        super(actor, fast ? 0.25f : DUR_DEFAULT);
        this.status = status;
        this.target = target;
    }

    public ApplyStatusAction(
            AbstractStatus status, AbstractEntity actor, AbstractSkill.SkillTarget target, boolean fast) {
        super(actor, target, fast ? 0.25f : DUR_DEFAULT);
        this.status = status;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                if (actor != null && actor.isPlayer) {
                    actor.passive.onApplyStatus(status, target);
                    for (AbstractItem m : actor.item) {
                        if (m != null) m.onApplyStatus(status, target);
                    }
                }
                if (status.type == AbstractStatus.StatusType.BUFF) {
                    SoundHandler.playSfx("BUFF");
                } else if (status.type == AbstractStatus.StatusType.DEBUFF) {
                    SoundHandler.playSfx("DEBUFF");
                } else {
                    SoundHandler.playSfx("STATIC");
                }
                for (AbstractEntity e : target) {
                    AbstractStatus s = status.cpy();
                    e.applyStatus(s, actor, status.amount);
                }
            } else {
                isDone = true;
            }
        }
    }
}
