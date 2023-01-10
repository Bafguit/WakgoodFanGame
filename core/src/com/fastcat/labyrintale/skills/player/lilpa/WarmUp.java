package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.GainEnergyAction;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.CounterStatus;
import com.fastcat.labyrintale.status.ParalyzedStatus;

public class WarmUp extends AbstractSkill {

    private static final String ID = "WarmUp";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 1;

    public WarmUp(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setBaseValue2(2, 1);
        setBaseCost(0);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new CounterStatus(value2), owner, owner, true));
        top(new ApplyStatusAction(new ParalyzedStatus(value), owner, e, true));
    }

    @Override
    protected void upgradeCard() {}
}
