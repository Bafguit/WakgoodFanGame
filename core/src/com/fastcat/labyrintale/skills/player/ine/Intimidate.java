package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.CriticalStatus;
import com.fastcat.labyrintale.status.SpeedStatus;

public class Intimidate extends AbstractSkill {

    private static final String ID = "Intimidate";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 2;

    public Intimidate(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setBaseValue2(10);
        setBaseCost(3);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new AttackStatus(value), owner, owner, true));
        bot(new ApplyStatusAction(new CriticalStatus(-value2), owner, owner, true));
    }

    @Override
    protected void upgradeCard() {}
}
