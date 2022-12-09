package com.fastcat.labyrintale.skills.player.basic;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class PassTurn extends AbstractSkill {

    private static final String ID = "PassTurn";
    private static final SkillType TYPE = SkillType.MOVE;
    private static final SkillRarity RARITY = SkillRarity.BASIC;
    private static final SkillTarget TARGET = SkillTarget.NONE;

    public PassTurn(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        cost = 0;
    }

    @Override
    public void use() {}

    @Override
    protected void upgradeCard() {}
}
