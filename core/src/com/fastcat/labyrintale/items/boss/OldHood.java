package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class OldHood extends AbstractItem {

    private static final String ID = "OldHood";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public OldHood(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.spell += 2;
        owner.stat.critical += 0.2f;
        owner.stat.neutRes += 0.2f;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(10);
        owner.stat.spell -= 2;
        owner.stat.critical -= 0.2f;
        owner.stat.neutRes -= 0.2f;
    }
}
