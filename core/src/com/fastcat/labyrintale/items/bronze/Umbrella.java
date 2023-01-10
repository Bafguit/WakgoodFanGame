package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.BlockAction;

public class Umbrella extends AbstractItem {

    private static final String ID = "Umbrella";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Umbrella(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        top(new BlockAction(owner, owner, 8));
        flash();
    }
}
