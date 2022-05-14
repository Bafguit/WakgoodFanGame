package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Item1 extends AbstractItem {

    private static final String ID = "Item1";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public Item1(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }
}
