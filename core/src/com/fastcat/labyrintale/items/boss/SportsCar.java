package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class SportsCar extends AbstractItem {

    private static final String ID = "SportsCar";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public SportsCar(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        AbstractLabyrinth.charge++;
        AbstractLabyrinth.modifyGold(150);
    }

    @Override
    public void onRemove() {
        AbstractLabyrinth.charge--;
    }
}
