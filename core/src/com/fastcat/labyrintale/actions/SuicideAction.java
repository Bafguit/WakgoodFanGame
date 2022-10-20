package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class SuicideAction extends AbstractAction {
    public SuicideAction(AbstractEntity target) {
        this(target, 0);
    }
    public SuicideAction(AbstractSkill.SkillTarget target) {
        this(target, 0);
    }

    public SuicideAction(AbstractEntity target, float duration) {
        super(null, target, duration);

    }

    public SuicideAction(AbstractSkill.SkillTarget target, float duration) {
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
