package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ChargeAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class Charge extends AbstractSkill {

    private static final String ID = "Charge";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 3;

    public Charge(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new MoveAction(owner, owner, false, 0.05f));
        bot(new ChargeAction(this, target));
    }

    @Override
    protected void upgradeCard() {}
}
