package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.LoseBlockAction;
import com.fastcat.labyrintale.status.UnblockableStatus;

public class FlameFlask extends AbstractSkill {

    private static final String ID = "FlameFlask";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
    private static final int VALUE = 2;

    public FlameFlask(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseCost(VALUE);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new UnblockableStatus(), owner, target, true));
        bot(new LoseBlockAction(owner, target));
    }

    @Override
    protected void upgradeCard() {
        if (cost > 0) cost--;
    }
}
