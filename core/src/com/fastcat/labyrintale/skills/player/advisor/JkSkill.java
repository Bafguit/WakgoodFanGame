package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class JkSkill extends AbstractSkill {

    private static final String ID = "jk";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;

    public JkSkill() {
        super(ID, TYPE, RARITY, TARGET);
        passive = true;
        setBaseValue(1);
    }

    @Override
    public void use() {

    }

    @Override
    protected void upgradeCard() {

    }
}
