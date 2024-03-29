package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.LethargyStatus;

public class ThrowAxe extends AbstractSkill {

    private static final String ID = "ThrowAxe";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 4;

    public ThrowAxe(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseValue(2, 1);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new LethargyStatus(value), owner, e, true));
        top(new AttackAction(owner, e, attack, AttackAction.AttackType.HEAVY, true));
    }

    @Override
    protected void upgradeCard() {}
}
