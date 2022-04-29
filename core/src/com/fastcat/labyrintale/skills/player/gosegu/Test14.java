package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.BurnStatus;

public class Test14 extends AbstractSkill {

    private static final String ID = "Test14";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.GOLD;
    private static final SkillTarget TARGET = SkillTarget.E_ALL;
    private static final int VALUE = 2;

    public Test14(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new BurnStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
