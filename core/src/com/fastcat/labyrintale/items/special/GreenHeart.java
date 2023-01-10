package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.HealAction;

public class GreenHeart extends AbstractItem {

    private static final String ID = "GreenHeart";
    private static final ItemRarity RARITY = ItemRarity.SPECIAL;

    public GreenHeart(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleEnd() {
        top(new HealAction(null, owner, 3));
        flash();
    }
}
