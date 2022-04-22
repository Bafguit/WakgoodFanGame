package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class Strong extends AbstractSkill {

    private static final String ID = "Strong";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.SS_L;
    private static final int VALUE = 4;

    public Strong(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {
        bot(new BlockAction(owner, TARGET, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
