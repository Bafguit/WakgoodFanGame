package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class PlaceHolder extends AbstractItem {

    private static final String ID = "PlaceHolder";
    private static final ItemRarity RARITY = ItemRarity.EMPTY;

    public PlaceHolder(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }
}
