package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Test73 extends AbstractSkill {

    private static final String ID = "Test73";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_ALL;
    private static final int VALUE = 30;

    public Test73(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHTNING));
    }

    @Override
    protected void upgradeCard() {

    }
}
