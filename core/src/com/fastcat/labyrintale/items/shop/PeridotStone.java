package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class PeridotStone extends AbstractItem {

    private static final String ID = "PeridotStone";
    private static final ItemRarity RARITY = ItemRarity.SHOP;

    public PeridotStone(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.goodLuck++;
    }

    @Override
    public void onRemove() {
        owner.goodLuck--;
    }
}
