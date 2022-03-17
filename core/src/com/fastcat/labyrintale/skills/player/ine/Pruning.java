package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Pruning extends AbstractSkill {

    private static final String ID = "Pruning";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.E_F;
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
        bot(new AttackAction(owner, target, attack, null));
        AbstractEnemy e = (AbstractEnemy) AbstractSkill.getTargets(target).get(0);
        if(e.mRight.canUse()) bot(new MoveAction(e, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
