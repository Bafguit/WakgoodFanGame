package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.FlawStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class UnblockE extends AbstractSkill {

    private static final String ID = "UnblockE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
    private static final int ATTACK = 4;
    private static final int VALUE = 1;

    public UnblockE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK, 1);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        AbstractAction a = new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH, true);
        bot(a);
        AbstractAction m = new ApplyStatusAction(new FlawStatus(value), owner, target, false);
        m.preAction = a;
        bot(m);
    }

    @Override
    protected void upgradeCard() {

    }
}
