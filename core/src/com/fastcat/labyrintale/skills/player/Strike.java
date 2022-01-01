package com.fastcat.labyrintale.skills.player;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;


public class Strike extends AbstractSkill {

    private static final String ID = "Strike";
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.E_F;
    private static final int VALUE = 5;

    public Strike(AbstractEntity e) {
        super(e, ID, RARITY, TARGET);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, TARGET, attack, null));
    }

    @Override
    protected void upgradeCard() {
        setBaseAttack(baseAttack + 2);
    }
}
