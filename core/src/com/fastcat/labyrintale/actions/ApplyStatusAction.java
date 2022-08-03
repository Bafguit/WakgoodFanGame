package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.*;

public class ApplyStatusAction extends AbstractAction {

    private final AbstractStatus status;

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, AbstractEntity target, boolean fast) {
        super(actor, fast ? 0.2f : DUR_DEFAULT);
        this.status = status;
        Array<AbstractEntity> e = new Array<>();
        e.add(target);
        this.target = e;
    }

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, Array<AbstractEntity> target, boolean fast) {
        super(actor, fast ? 0.2f : DUR_DEFAULT);
        this.status = status;
        this.target = target;
    }

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, AbstractSkill.SkillTarget target, boolean fast) {
        super(actor, target, fast ? 0.2f : DUR_DEFAULT);
        this.status = status;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (actor != null && actor.isPlayer) {
                for (AbstractItem m : actor.item) {
                    if (m != null) m.onApplyStatus(status, target);
                }
            }

            for (AbstractEntity e : target) {
                e.applyStatus(status, status.amount);
            }
        }
    }
}
