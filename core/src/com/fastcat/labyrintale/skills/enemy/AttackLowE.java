package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;
import com.fastcat.labyrintale.actions.LowAttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class AttackLowE extends AbstractSkill {

    private static final String ID = "AttackLowE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_LOW_HP;
    private static final int VALUE = 4;

    public AttackLowE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new LowAttackAction(owner, attack, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
