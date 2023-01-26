package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Butter extends AbstractItem {

    private static final String ID = "Butter";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public Butter(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        AbstractLabyrinth.charge++;
        owner.modifyMaxHealth(-5);
        owner.stat.speed -= 2;
        consume();
    }
}
