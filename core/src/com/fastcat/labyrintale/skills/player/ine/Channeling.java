package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Channeling extends AbstractSkill {

    private static final String ID = "Channeling";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_LAST;
    private static final int VALUE = 1;

    public Channeling(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        AbstractEnemy e = (AbstractEnemy) AbstractSkill.getTargets(target).get(0);
        bot(new MoveAction(e, owner, 0, 0.2f));
        bot(new ApplyStatusAction(new UnfortifiedStatus(value), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
