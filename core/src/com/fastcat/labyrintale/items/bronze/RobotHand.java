package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class RobotHand extends AbstractItem {

    private static final String ID = "RobotHand";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public RobotHand(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.attack += 1;
        owner.stat.critical += 15;
    }

    @Override
    public void onRemove() {
        owner.stat.attack -= 1;
        owner.stat.critical -= 15;
    }
}
