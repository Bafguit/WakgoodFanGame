package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.LethargyStatus;

public class ThrowAxe extends AbstractSkill {

    private static final String ID = "ThrowAxe";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_LAST;
    private static final int VALUE = 5;

    public ThrowAxe(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseValue(1, 1);
    }

    @Override
    public void use() {
        AbstractAction a = new AttackAction(owner, target, attack, AttackAction.AttackType.HEAVY, true);
        bot(a);
        AbstractAction m = new ApplyStatusAction(new LethargyStatus(value), owner, target, true);
        m.preAction = a;
        bot(m);
    }

    @Override
    protected void upgradeCard() {

    }
}
