package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.CounterStatus;

public class Sharp extends AbstractSkill {

    private static final String ID = "Sharp";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 3;

    public Sharp(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new BlockAction(this.owner, target, spell));
        top(new ApplyStatusAction(new CounterStatus(value), owner, owner, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
