package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.*;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.NONE;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;

public class StatusSelfDamageAction extends AbstractAction {

    public AbstractStatus status;
    public boolean reduce = false;
    public boolean done = false;

    public StatusSelfDamageAction(AbstractStatus s) {
        super(s.owner, NONE, 0.5f);
        status = s;
    }

    public StatusSelfDamageAction(AbstractStatus s, boolean reduce) {
        this(s);
        this.reduce = reduce;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            status.flash(actor);
            actor.takeDamage(actor, status.amount);
            if(reduce) actor.applyStatus(status, -1);
        }
    }
}
