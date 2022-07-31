package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class UpgradeAction extends AbstractAction {

    private final AbstractSkill[] skills;

    public UpgradeAction(AbstractSkill[] skills) {
        super(null, AbstractSkill.SkillTarget.NONE, 0.2f);
        this.skills = skills;
    }

    public UpgradeAction(AbstractSkill skill) {
        this(new AbstractSkill[]{skill});
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            for (AbstractSkill s : skills) {
                s.upgrade();
            }
        }
    }
}
