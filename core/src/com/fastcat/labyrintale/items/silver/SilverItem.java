package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SilverItem extends AbstractItem {

    private static final String ID = "SilverItem";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public SilverItem(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
    }
}
