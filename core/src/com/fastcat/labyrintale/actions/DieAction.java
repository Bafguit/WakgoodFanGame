package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class DieAction extends AbstractAction {
    public DieAction(AbstractEntity target) {
        this(target, 0);
    }
    public DieAction(AbstractSkill.SkillTarget target) {
        this(target, 0);
    }

    public DieAction(AbstractEntity target, float duration) {
        super(null, target, duration);

    }

    public DieAction(AbstractSkill.SkillTarget target, float duration) {
        super(null, target, duration);
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            if(target != null) {
                for(AbstractEntity e : target) {
                    e.die(actor);
                }
            }
        }
    }
}
