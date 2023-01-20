package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Shield extends AbstractItem {

    private static final String ID = "Shield";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Shield(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(12);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-12);
    }
}
