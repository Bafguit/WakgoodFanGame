package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.CriticalPlusStatus;

public class BraveWill extends AbstractSkill {

    private static final String ID = "BraveWill";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 2;

    public BraveWill(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setBaseCost(2);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new CourageStatus(value), owner, target, true));
        bot(new ApplyStatusAction(new CriticalPlusStatus(value), owner, target, true));
    }

    @Override
    protected void upgradeCard() {}
}
