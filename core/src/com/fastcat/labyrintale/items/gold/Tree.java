package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Tree extends AbstractItem {

    private static final String ID = "Tree";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public Tree(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.critical += 30;
        owner.stat.debuRes += 10;
        owner.stat.neutRes += 10;
        owner.stat.moveRes += 10;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
        owner.stat.critical -= 30;
        owner.stat.debuRes -= 10;
        owner.stat.neutRes -= 10;
        owner.stat.moveRes -= 10;
    }
}
