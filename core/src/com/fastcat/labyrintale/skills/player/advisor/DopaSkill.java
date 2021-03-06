package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class DopaSkill extends AbstractSkill {

    private static final String ID = "dopa";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;
    private static final int VALUE = 2;

    public DopaSkill() {
        super(ID, TYPE, RARITY, TARGET);
        passive = true;
    }

    @Override
    public void use() {

    }

    @Override
    protected void upgradeCard() {

    }
}
