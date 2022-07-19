package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class FreeterSkill extends AbstractSkill {

    private static final String ID = "freeter";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;

    public FreeterSkill() {
        super(ID, TYPE, RARITY, TARGET);
        passive = true;
        setBaseValue(1, 1);
    }

    @Override
    public void use() {

    }

    public void atBattleStart() {
        bot(new ApplyStatusAction(new CourageStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
