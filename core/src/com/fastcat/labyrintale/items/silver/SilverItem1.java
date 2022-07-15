package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SilverItem1 extends AbstractItem {

    private static final String ID = "SilverItem1";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public SilverItem1(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
    }
}
