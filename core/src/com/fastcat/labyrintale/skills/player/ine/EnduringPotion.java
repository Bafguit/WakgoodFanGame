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
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 25;

    public EnduringPotion(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 10);
        setBaseValue(0);
        setBaseCost(2);
    }

    @Override
    public void use() {
        ActionHandler.bot(new HealAction(owner, TARGET, value));
    }

    @Override
    protected void upgradeCard() {

    }

    @Override
    public int calculateValue(int a) {
        return (int) ((float) owner.maxHealth * owner.calculateSpell(spell) * 0.01f);
    }
}
