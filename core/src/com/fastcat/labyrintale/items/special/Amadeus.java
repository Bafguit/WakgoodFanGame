package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Amadeus extends AbstractItem {

    private static final String ID = "Amadeus";
    private static final ItemRarity RARITY = ItemRarity.SPECIAL;

    public Amadeus(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.debuRes += 20;
        }
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.debuRes -= 20;
        }
    }
}
