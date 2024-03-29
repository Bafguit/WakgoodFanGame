package com.fastcat.labyrintale.items.special;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class DevilIdol extends AbstractItem {

    private static final String ID = "DevilIdol";
    private static final ItemRarity RARITY = ItemRarity.SPECIAL;

    public DevilIdol(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.speed += 5;
        }
        owner.minRes = 88;
        owner.maxRes = 88;
        AbstractLabyrinth.scoreHandle.devil = true;
    }

    @Override
    public void onRemove() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.speed -= 5;
        }
        owner.minRes = 5;
        owner.maxRes = 80;
    }
}
