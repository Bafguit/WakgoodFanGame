package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class TrafficLight extends AbstractItem {

    private static final String ID = "TrafficLight";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public TrafficLight(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        AbstractLabyrinth.charge++;
        owner.stat.speed += 1;
    }

    @Override
    public void onRemove() {
        AbstractLabyrinth.charge--;
        owner.stat.speed -= 1;
    }
}
