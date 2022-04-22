package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.SpellStatus;

public class Encourage extends AbstractSkill {

    private static final String ID = "Encourage";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.S_B;
    private static final int VALUE = 2;

    public Encourage(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
        setBaseValue(1, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
        bot(new ApplyStatusAction(new CourageStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
