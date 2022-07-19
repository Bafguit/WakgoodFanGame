package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class BusinessSkill extends AbstractSkill {

    private static final String ID = "business";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_LAST_TWO;

    public BusinessSkill() {
        super(ID, TYPE, RARITY, TARGET);
        setBaseValue(1, 1);
        passive = true;
    }

    @Override
    public void use() {

    }

    public void atBattleStart() {
        bot(new ApplyStatusAction(new EnduranceStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
