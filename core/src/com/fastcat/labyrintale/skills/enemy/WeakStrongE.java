package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class WeakStrongE extends AbstractSkill {

    private static final String ID = "WeakStrongE";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 2;

    public WeakStrongE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setIntent(IntentType.DEBUFF);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new UnfortifiedStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {}
}
