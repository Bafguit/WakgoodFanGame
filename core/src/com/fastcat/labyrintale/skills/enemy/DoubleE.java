package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class DoubleE extends AbstractSkill {

    private static final String ID = "DoubleE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
    private static final int VALUE = 2;

    public DoubleE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.SLASH_H, true));
        bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.SLASH_V, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
