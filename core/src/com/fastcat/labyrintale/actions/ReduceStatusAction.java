package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.NONE;

public class ReduceStatusAction extends AbstractAction {

    public AbstractStatus status;
    public int amount;

    public ReduceStatusAction(AbstractStatus s, int amount, boolean isFast) {
        super(s.owner, NONE, isFast ? 0.2f : 0.5f);
        status = s;
        this.amount = amount;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            actor.applyStatus(status, -amount);
        }
    }
}
