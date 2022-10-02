package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class ToyPlane extends AbstractItem {

    private static final String ID = "ToyPlane";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public ToyPlane(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(-10);
        owner.stat.spell -= 10;
        owner.stat.speed += 100;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(+10);
        owner.stat.spell += 10;
        owner.stat.speed -= 100;
    }
}
