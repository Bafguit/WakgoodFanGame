package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BurnStatus;

public class FireBall extends AbstractSkill {

    private static final String ID = "FireBall";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_DF;
    private static final int VALUE = 2;

    public FireBall(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.BURN));
        bot(new ApplyStatusAction(new BurnStatus(value), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
