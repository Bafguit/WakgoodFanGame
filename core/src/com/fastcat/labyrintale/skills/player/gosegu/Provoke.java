package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ResistMinusStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Provoke extends AbstractSkill {

    private static final String ID = "Provoke";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 2;

    public Provoke(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new ResistMinusStatus(value), owner, e, true));
        top(new ApplyStatusAction(new UnfortifiedStatus(value), owner, e, true));
    }

    @Override
    protected void upgradeCard() {}
}
