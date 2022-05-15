package com.fastcat.labyrintale.skills.player.basic;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.InfectionStatus;


public class Spike extends AbstractSkill {

    private static final String ID = "Spike";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.E_DF;
    private static final int VALUE = 4;

    public Spike(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new ApplyStatusAction(new InfectionStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {
        setBaseValue(baseValue + 3);
    }
}
