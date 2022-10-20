package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.*;
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
        AbstractAction a = new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH, true);
        bot(a);
        AbstractEntity e = AbstractSkill.getTargets(target).get(0);
        AbstractAction m = new MoveAction(e, owner, 3, 0.2f);
        m.preAction = a;
        bot(m);
    }

    @Override
    protected void upgradeCard() {

    }
}
