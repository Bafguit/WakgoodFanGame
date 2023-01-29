package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.CatalystAction;
import com.fastcat.labyrintale.status.ResistMinusStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Catalyst extends AbstractSkill {

    private static final String ID = "Catalyst";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
    private static final int VALUE = 2;

    public Catalyst(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setBaseCost(4);
        disposable = true;
    }

    @Override
    public void use() {
        bot(new CatalystAction(owner, TARGET, value, false));
    }

    @Override
    protected void upgradeCard() {}
}
