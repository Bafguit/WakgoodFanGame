package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class DamageAction extends AbstractAction {
    public DamageAction(AbstractEntity actor, AbstractSkill.SkillTarget target, AbstractEffect effect, float duration) {
        super(actor, target, duration);
    }

    @Override
    protected void updateAction() {

    }
}
