package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Bible extends AbstractItem {

    private static final String ID = "Bible";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public Bible(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }
}
