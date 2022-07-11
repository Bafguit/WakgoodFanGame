package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;

public class ThrowAxe extends AbstractSkill {

    private static final String ID = "ThrowAxe";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_LAST;
    private static final int VALUE = 5;

    public ThrowAxe(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.HEAVY));
    }

    @Override
    protected void upgradeCard() {

    }
}
