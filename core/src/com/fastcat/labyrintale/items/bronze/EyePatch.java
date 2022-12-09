package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class EyePatch extends AbstractItem {

    private static final String ID = "EyePatch";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public EyePatch(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.spell += 1;
        owner.stat.debuRes += 15;
    }

    @Override
    public void onRemove() {
        owner.stat.spell -= 1;
        owner.stat.debuRes -= 15;
    }
}
