package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ParalyzedStatus;
import com.fastcat.labyrintale.status.ShockStatus;

public class ShockZone extends AbstractSkill {

    private static final String ID = "ShockZone";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
    private static final int VALUE = 4;

    public ShockZone(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setBaseValue2(1, 1);
        setBaseCost(2);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new ShockStatus(value), owner, target, true));
        bot(new ApplyStatusAction(new ParalyzedStatus(value2), owner, target, true));
    }

    @Override
    protected void upgradeCard() {}
}
