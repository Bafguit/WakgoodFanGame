package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class OldShield extends AbstractItem {

    private static final String ID = "OldShield";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public OldShield(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }
}
