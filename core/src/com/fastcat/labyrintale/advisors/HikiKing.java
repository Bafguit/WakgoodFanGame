package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class HikiKing extends AbstractItem {

    private static final String ID = "hiki";
    private static final ItemRarity RARITY = ItemRarity.ADVISOR;

    public HikiKing() {
        super(ID, null, RARITY);
    }

    @Override
    public void onGain() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.attack++;
            p.stat.critical += 20;
        }
    }

    @Override
    public void onRemove() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.attack--;
            p.stat.critical -= 20;
        }
    }
}
