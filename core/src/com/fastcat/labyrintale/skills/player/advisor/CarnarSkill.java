package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.LowHealAction;

public class CarnarSkill extends AbstractSkill {

    private static final String ID = "carnar";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;
    private static final int VALUE = 2;

    public CarnarSkill() {
        super(ID, TYPE, RARITY, TARGET);
        passive = true;
        setBaseValue(VALUE);
    }

    @Override
    public void use() {

    }

    @Override
    public void atBattleEnd() {
        bot(new LowHealAction(value));
    }

    @Override
    protected void upgradeCard() {

    }
}
