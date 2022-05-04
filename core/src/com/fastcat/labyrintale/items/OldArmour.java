package com.fastcat.labyrintale.items;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class OldArmour extends AbstractItem {

    private static final String ID = "OldArmour";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public OldArmour(AbstractPlayer owner) {
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
