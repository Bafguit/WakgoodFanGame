package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.FixedStatus;

public class InduceE extends AbstractSkill {

    private static final String ID = "InduceE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_LAST;

    public InduceE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
    }

    @Override
    public void use() {
        AbstractEntity e = AbstractSkill.getTargets(target).get(0);
        bot(new MoveAction(e, 0, 0.2f));
        bot(new ApplyStatusAction(new FixedStatus(), owner, e, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
