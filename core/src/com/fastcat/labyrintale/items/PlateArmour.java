package com.fastcat.labyrintale.items;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class PlateArmour extends AbstractItem {

    private static final String ID = "PlateArmour";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public PlateArmour(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(3);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-3);
    }
}
