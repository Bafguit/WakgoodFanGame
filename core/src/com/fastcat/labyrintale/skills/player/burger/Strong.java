package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.FixedStatus;

public class Strong extends AbstractSkill {

    private static final String ID = "Strong";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.GOLD;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 4;

    public Strong(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        top(new BlockAction(owner, owner, spell));
        top(new ApplyStatusAction(new FixedStatus(owner), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
