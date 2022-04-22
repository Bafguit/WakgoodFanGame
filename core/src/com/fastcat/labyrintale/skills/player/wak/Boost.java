package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.SpellStatus;

public class Boost extends AbstractSkill {

    private static final String ID = "Boost";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.GOLD;
    private static final SkillTarget TARGET = SkillTarget.P_ALL;
    private static final int VALUE = 1;

    public Boost(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        cooltime = 3;
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new SpellStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
