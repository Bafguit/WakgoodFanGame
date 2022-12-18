package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SecretGirl extends AbstractItem {

    private static final String ID = "secret";
    private static final ItemRarity RARITY = ItemRarity.ADVISOR;

    public SecretGirl() {
        super(ID, null, RARITY);
    }

    @Override
    public void onGain() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.goodLuck++;
        }
    }

    @Override
    public void onRemove() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.goodLuck--;
        }
    }
}
