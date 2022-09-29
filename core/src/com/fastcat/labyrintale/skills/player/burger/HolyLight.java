package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class HolyLight extends AbstractSkill {

    private static final String ID = "HolyLight";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 2;

    public HolyLight(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);

    }

    @Override
    public void use() {
        ActionHandler.bot(new HealAction(owner, TARGET, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
