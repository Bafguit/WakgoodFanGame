package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Tail extends AbstractItem {

    private static final String ID = "Tail";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Tail(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        owner.stat.speed += 2;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        owner.stat.speed -= 2;
    }
}
