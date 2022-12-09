package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class FlameBook extends AbstractItem {

    private static final String ID = "FlameBook";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public FlameBook(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }
}
