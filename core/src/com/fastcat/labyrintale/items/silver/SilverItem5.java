package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SilverItem5 extends AbstractItem {

    private static final String ID = "SilverItem5";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public SilverItem5(AbstractPlayer owner) {
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
