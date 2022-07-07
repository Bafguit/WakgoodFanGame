package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Insight extends AbstractSkill {

    private static final String ID = "Insight";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.ALL;
    private static final int VALUE = 2;

    public Insight(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
