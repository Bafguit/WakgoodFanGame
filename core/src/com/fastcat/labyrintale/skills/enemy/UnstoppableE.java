package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;
import com.fastcat.labyrintale.actions.UnstoppableAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class UnstoppableE extends AbstractSkill {

    private static final String ID = "UnstoppableE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
    private static final int VALUE = 1;

    public UnstoppableE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 3);
        cooltime = 1;
    }

    @Override
    public void use() {
        bot(new UnstoppableAction(this));
    }

    @Override
    protected void upgradeCard() {

    }
}
