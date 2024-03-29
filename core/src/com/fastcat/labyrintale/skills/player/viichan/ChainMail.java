package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class ChainMail extends AbstractSkill {

    private static final String ID = "ChainMail";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 4;

    public ChainMail(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
    }

    @Override
    protected void upgradeCard() {}
}
