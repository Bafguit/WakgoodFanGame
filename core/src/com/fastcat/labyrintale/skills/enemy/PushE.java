package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class PushE extends AbstractSkill {

    private static final String ID = "PushE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
    private static final int ATTACK = 4;
    private static final int UP = 1;
    private static final int VALUE = 1;

    public PushE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK, UP);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH, true));
        AbstractEntity e = AbstractSkill.getTargets(target).get(0);
        bot(new MoveAction(e, 3, 0.2f));
    }

    @Override
    protected void upgradeCard() {

    }
}
