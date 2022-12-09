package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Spaceship extends AbstractItem {

    private static final String ID = "Spaceship";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public Spaceship(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        AbstractLabyrinth.charge++;
        owner.stat.critical += 15;
    }

    @Override
    public void onRemove() {
        AbstractLabyrinth.charge--;
        owner.stat.critical -= 15;
    }
}
