package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

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
