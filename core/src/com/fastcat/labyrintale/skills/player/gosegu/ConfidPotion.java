package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;
import com.fastcat.labyrintale.status.InfectionStatus;

public class ConfidPotion extends AbstractSkill {

    private static final String ID = "ConfidPotion";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 3;

    public ConfidPotion(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new InfectionStatus(2), owner, e, true));
        top(new ApplyStatusAction(new EnduranceStatus(value), owner, e, true));
        top(new ApplyStatusAction(new CourageStatus(value), owner, e, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
