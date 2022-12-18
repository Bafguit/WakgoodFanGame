package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
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
        setIntent(IntentType.ATTACK);
    }

    @Override
    public void use() {
        AbstractAction a;
        bot(a = new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH, true));
        bot(new MoveAction(AbstractSkill.getTargets(target).get(0), owner, 3, 0.2f), a);
    }

    @Override
    protected void upgradeCard() {}
}
