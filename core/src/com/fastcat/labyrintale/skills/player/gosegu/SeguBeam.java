package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.InfectionStatus;

public class SeguBeam extends AbstractSkill {

    private static final String ID = "SeguBeam";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_ALL;
    private static final int VALUE = 2;

    public SeguBeam(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new ApplyStatusAction(new InfectionStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
