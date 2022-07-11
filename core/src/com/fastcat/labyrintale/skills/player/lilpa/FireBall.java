package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BurnStatus;

public class FireBall extends AbstractSkill {

    private static final String ID = "FireBall";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST_TWO;
    private static final int VALUE = 2;

    public FireBall(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.BURN, true));
        bot(new ApplyStatusAction(new BurnStatus(value), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
