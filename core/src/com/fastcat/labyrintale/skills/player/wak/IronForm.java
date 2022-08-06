package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.EnduranceStatus;
import com.fastcat.labyrintale.status.FixedStatus;

public class IronForm extends AbstractSkill {

    private static final String ID = "IronForm";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 1;

    public IronForm(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new EnduranceStatus(value), owner, target, true));
        bot(new ApplyStatusAction(new FixedStatus(), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
