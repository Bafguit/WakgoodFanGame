package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class PlatedArmour extends AbstractItem {

    private static final String ID = "PlatedArmour";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public PlatedArmour(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(8);
        owner.stat.moveRes += 8;
        owner.stat.debuRes += 8;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-8);
        owner.stat.moveRes -= 8;
        owner.stat.debuRes -= 8;
    }
}
