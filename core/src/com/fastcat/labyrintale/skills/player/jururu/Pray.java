package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Pray extends AbstractSkill {

    private static final String ID = "Pray";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.SELF_BOTH;
    private static final int VALUE = 1;

    public Pray(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new CourageStatus(value), owner, target, true));
        bot(new ApplyStatusAction(new EnduranceStatus(1), owner, target, true));
    }

    @Override
    protected void upgradeCard() {}
}
