package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;

public class KimchiMandu extends AbstractItem {

    private static final String ID = "mandu";
    private static final ItemRarity RARITY = ItemRarity.ADVISOR;

    public KimchiMandu() {
        super(ID, null, RARITY);
    }

    @Override
    public void onGain() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.moveRes += 60;
        }
    }

    @Override
    public void onRemove() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.moveRes -= 60;
        }
    }
}
