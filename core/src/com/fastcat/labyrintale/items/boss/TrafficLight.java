package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class TrafficLight extends AbstractItem {

    private static final String ID = "TrafficLight";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public TrafficLight(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.speed += 2;
        owner.stat.moveRes += 0.3f;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
        owner.stat.speed -= 2;
        owner.stat.moveRes -= 0.3f;
    }
}