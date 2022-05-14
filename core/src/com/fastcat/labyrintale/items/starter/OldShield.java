package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class OldShield extends AbstractItem {

    private static final String ID = "OldShield";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public OldShield(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new BlockAction(owner, AbstractSkill.SkillTarget.P_ALL, 1));
    }
}
