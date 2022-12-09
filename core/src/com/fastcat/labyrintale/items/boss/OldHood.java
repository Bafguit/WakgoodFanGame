package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class OldHood extends AbstractItem {

    private static final String ID = "OldHood";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public OldHood(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        AbstractLabyrinth.charge++;
        owner.stat.neutRes += 10;
    }

    @Override
    public void onRemove() {
        AbstractLabyrinth.charge--;
        owner.stat.neutRes -= 10;
    }
}
