package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class CarnarSkill extends AbstractSkill {

    private static final String ID = "carnar";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;
    private static final int VALUE = 2;

    public CarnarSkill() {
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
