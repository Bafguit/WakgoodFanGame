package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SuperLadle extends AbstractItem {

    private static final String ID = "SuperLadle";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public SuperLadle(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.attack += 2;
        owner.stat.critical += 10;
        owner.stat.multiply += 10;
    }

    @Override
    public void onRemove() {
        owner.stat.attack -= 2;
        owner.stat.critical -= 10;
        owner.stat.multiply -= 10;
    }
}
