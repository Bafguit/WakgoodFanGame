package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Juggernaut extends AbstractItem {

    private static final String ID = "Juggernaut";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public Juggernaut(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.debuRes += 0.1f;
        owner.stat.neutRes += 0.1f;
        owner.stat.moveRes += 0.1f;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
        owner.stat.debuRes -= 0.1f;
        owner.stat.neutRes -= 0.1f;
        owner.stat.moveRes -= 0.1f;
    }
}
