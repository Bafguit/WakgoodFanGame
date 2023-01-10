package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.RestrictStatus;
import com.fastcat.labyrintale.status.UnblockableStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Lure extends AbstractSkill {

    private static final String ID = "Lure";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 2;

    public Lure(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new UnfortifiedStatus(value), owner, e, true));
        top(new ApplyStatusAction(new RestrictStatus(value), owner, e, true));
        top(new MoveAction(e, owner, 0, 0.2f));
    }

    @Override
    protected void upgradeCard() {}
}
