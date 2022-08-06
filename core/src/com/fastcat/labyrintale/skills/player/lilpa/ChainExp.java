package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class ChainExp extends AbstractSkill {

    private static final String ID = "ChainExp";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 1;

    public ChainExp(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new AttackAction(owner, SkillTarget.ENEMY_ALL, attack, AttackAction.AttackType.BURN));
        top(new AttackAction(owner, e, attack, AttackAction.AttackType.LIGHTNING));
    }

    @Override
    protected void upgradeCard() {

    }
}
