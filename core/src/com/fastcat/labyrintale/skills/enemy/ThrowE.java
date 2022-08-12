package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class ThrowE extends AbstractSkill {

    private static final String ID = "ThrowE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
    private static final int ATTACK = 5;
    private static final int UP = 1;

    public ThrowE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK, UP);
        cooltime = 1;
    }

    @Override
    public void use() {
        bot(new AmbushAction(this, true));
        AbstractEntity e = AbstractSkill.getTargets(target).get(0);
        bot(new MoveAction(e, 3, 0.2f));
    }

    @Override
    protected void upgradeCard() {

    }
}
