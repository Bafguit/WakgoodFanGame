package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Rose extends AbstractItem {

    private static final String ID = "Rose";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public Rose(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.spell += 3;
        owner.stat.speed += 3;
    }

    @Override
    public void onRemove() {
        owner.stat.spell -= 3;
        owner.stat.speed -= 3;
    }
}
