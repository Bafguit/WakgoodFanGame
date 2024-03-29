package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class FuryE extends AbstractSkill {

    private static final String ID = "FuryE";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 1;

    public FuryE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setIntent(IntentType.BUFF);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new AttackStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {}
}
