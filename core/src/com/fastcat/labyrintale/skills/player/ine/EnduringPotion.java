package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class EnduringPotion extends AbstractSkill {

    private static final String ID = "EnduringPotion";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 2;

    public EnduringPotion(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(2);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new HealAction(owner, TARGET, spell));
        ActionHandler.bot(new ApplyStatusAction(new EnduranceStatus(value), owner, TARGET, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
