package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class FreeterSkill extends AbstractSkill {

    private static final String ID = "freeter";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;
    private static final int VALUE = 2;

    public FreeterSkill() {
        super(ID, TYPE, RARITY, TARGET);
        //passive = true;
    }

    @Override
    public void use() {

    }

    @Override
    protected void upgradeCard() {

    }
}
