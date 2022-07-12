package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.ShockStatus;

public class ElectricFire extends AbstractSkill {

    private static final String ID = "ElectricFire";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
    private static final int VALUE = 1;

    public ElectricFire(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new BurnStatus(value), owner, target, true));
        bot(new ApplyStatusAction(new ShockStatus(value), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
