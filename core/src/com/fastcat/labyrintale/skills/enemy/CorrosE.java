package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.FlawStatus;

public class CorrosE extends AbstractSkill {

    private static final String ID = "CorrosE";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST_TWO;
    private static final int VALUE = 1;

    public CorrosE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setIntent(IntentType.DEBUFF);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new FlawStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {}
}
