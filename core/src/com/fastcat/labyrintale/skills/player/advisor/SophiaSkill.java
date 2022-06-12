package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class SophiaSkill extends AbstractSkill {

    private static final String ID = "sophia";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;
    public static final float MULTIPLE = 1.2f;

    public SophiaSkill() {
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
