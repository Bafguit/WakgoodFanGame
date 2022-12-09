package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;

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
