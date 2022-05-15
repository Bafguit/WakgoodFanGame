package com.fastcat.labyrintale.skills.player.basic;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;


public class Strike extends AbstractSkill {

    private static final String ID = "Strike";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.E_F;
    private static final int VALUE = 4;

    public Strike(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE,1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHT));
    }

    @Override
    protected void upgradeCard() {

    }
}
