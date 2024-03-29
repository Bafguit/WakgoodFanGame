package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class GolemHead extends AbstractItem {

    private static final String ID = "GolemHead";
    private static final ItemRarity RARITY = ItemRarity.SPECIAL;

    public GolemHead(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            p.modifyMaxHealth(20);
        }
    }

    @Override
    public void onRemove() {
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            p.modifyMaxHealth(-20);
        }
    }
}
