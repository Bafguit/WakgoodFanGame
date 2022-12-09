package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.status.ProvokeStatus;

public class GuardE extends AbstractSkill {

    private static final String ID = "GuardE";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 3;

    public GuardE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
        setIntent(IntentType.SHIELD_BUFF);
    }

    @Override
    public void use() {
        bot(new BlockAction(owner, owner, spell));
        bot(new ApplyStatusAction(new ProvokeStatus(owner), owner, owner, true));
    }

    @Override
    protected void upgradeCard() {}
}
