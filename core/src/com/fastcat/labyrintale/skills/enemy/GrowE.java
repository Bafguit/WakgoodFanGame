package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class GrowE extends AbstractSkill {

    private static final String ID = "GrowE";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 4;

    public GrowE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
        setBaseValue(2, 1);
        setIntent(IntentType.SHIELD_BUFF);
    }

    @Override
    public void use() {
        bot(new BlockAction(this.owner, target, spell));
        bot(new ApplyStatusAction(new AttackStatus(value), owner, target, true));
    }

    @Override
    protected void upgradeCard() {}
}
