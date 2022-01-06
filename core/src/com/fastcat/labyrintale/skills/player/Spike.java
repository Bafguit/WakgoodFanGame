package com.fastcat.labyrintale.skills.player;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.BleedingStatus;


public class Spike extends AbstractSkill {

    private static final String ID = "Spike";
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.E_DF;
    private static final int VALUE = 4;

    public Spike(AbstractEntity e) {
        super(e, ID, RARITY, TARGET);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new ApplyStatusAction(new BleedingStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {
        setBaseValue(baseValue + 3);
    }
}
