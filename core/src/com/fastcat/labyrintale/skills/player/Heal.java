package com.fastcat.labyrintale.skills.player;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Heal extends AbstractSkill {

    private static final String ID = "Heal";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.P_ALL;
    private static final int VALUE = 2;

    public Heal(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new HealAction(owner, TARGET, value));
    }

    @Override
    protected void upgradeCard() {

    }
}
