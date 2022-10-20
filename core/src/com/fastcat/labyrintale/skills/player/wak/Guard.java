package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.status.ProvokeStatus;

public class Guard extends AbstractSkill {

    private static final String ID = "Guard";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 6;

    public Guard(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
        setBaseCost(2);
    }

    @Override
    public void use() {
        bot(new BlockAction(owner, owner, spell));
        bot(new ApplyStatusAction(new ProvokeStatus(owner), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
