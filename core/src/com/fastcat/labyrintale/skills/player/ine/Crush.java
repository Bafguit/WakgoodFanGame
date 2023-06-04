package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.ChargeAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.LethargyStatus;
import com.fastcat.labyrintale.status.UnblockableStatus;

public class Crush extends AbstractSkill {

    private static final String ID = "Crush";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 5;

    public Crush(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseValue(1, 1);
    }

    @Override
    public void use() {
        AbstractAction a;
        bot(a = new AttackAction(owner, TARGET, attack, AttackAction.AttackType.SMASH, true));
        bot(new ApplyStatusAction(new UnblockableStatus(value), owner, TARGET, true), a);
    }

    @Override
    protected void upgradeCard() {}

    @Override
    public int calculateValue(int v) {
        return attack / 2;
    }
}
