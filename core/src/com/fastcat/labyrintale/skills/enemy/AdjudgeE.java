package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.FixedStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class AdjudgeE extends AbstractSkill {

    private static final String ID = "AdjudgeE";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;

    public AdjudgeE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(1, 1);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new FixedStatus(), owner, target, false));
        bot(new ApplyStatusAction(new UnfortifiedStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
