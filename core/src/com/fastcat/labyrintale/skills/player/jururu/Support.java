package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.SpeedPlusStatus;

public class Support extends AbstractSkill {

    private static final String ID = "Support";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;

    public Support(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(1, 1);
        setBaseCost(3);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new SpeedPlusStatus(value), owner, e, true));
        top(new ApplyStatusAction(new AttackStatus(value), owner, e, true));
    }

    @Override
    protected void upgradeCard() {}
}
