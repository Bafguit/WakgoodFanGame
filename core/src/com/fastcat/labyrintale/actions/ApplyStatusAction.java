package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;

public class ApplyStatusAction extends AbstractAction {

    private final AbstractStatus status;

    public ApplyStatusAction(AbstractStatus status, AbstractEntity actor, AbstractSkill.SkillTarget target, boolean fast) {
        super(actor, target, fast ? 0.25f : DUR_DEFAULT);
        this.status = status;
        this.status.source = this.actor;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            for(AbstractEntity e : target) {
                e.applyStatus(status, status.amount);
            }
        }
    }
}
