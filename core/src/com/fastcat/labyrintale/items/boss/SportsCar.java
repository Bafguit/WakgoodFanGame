package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SportsCar extends AbstractItem {

    private static final String ID = "SportsCar";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public SportsCar(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.speed += 30;
        owner.stat.critical += 0.3f;
        owner.stat.multiply += 0.3f;
        owner.stat.moveRes -= 0.3f;
    }

    @Override
    public void onRemove() {
        owner.stat.speed -= 30;
        owner.stat.critical -= 0.3f;
        owner.stat.multiply -= 0.3f;
        owner.stat.moveRes += 0.3f;
    }
}
