package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class Pruning extends AbstractSkill {

    private static final String ID = "Pruning";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int ATTACK = 4;
    private static final int UP = 1;
    private static final int VALUE = 1;

    public Pruning(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK, UP);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH, true));
        AbstractEnemy e = (AbstractEnemy) AbstractSkill.getTargets(target).get(0);
        bot(new MoveAction(e, 3, 0.2f));
    }

    @Override
    protected void upgradeCard() {

    }
}
