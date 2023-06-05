package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Water extends AbstractItem {

    private static final String ID = "Water";
    private static final ItemRarity RARITY = ItemRarity.SPECIAL;

    public Water(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        owner.stat.attack += 1;
        owner.stat.spell += 1;
        owner.stat.speed += 1;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        owner.stat.attack -= 1;
        owner.stat.spell -= 1;
        owner.stat.speed -= 1;
    }
}
