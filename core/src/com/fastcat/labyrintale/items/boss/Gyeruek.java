package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Gyeruek extends AbstractItem {

    private static final String ID = "Gyeruek";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public Gyeruek(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(8);
        owner.stat.attack += 1;
        owner.stat.spell += 1;
        owner.stat.speed += 1;
        owner.stat.critical += 8;
        owner.stat.neutRes += 8;
        owner.stat.debuRes += 8;
        owner.stat.moveRes += 8;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-8);
        owner.stat.attack -= 1;
        owner.stat.spell -= 1;
        owner.stat.speed -= 1;
        owner.stat.critical -= 8;
        owner.stat.neutRes -= 8;
        owner.stat.debuRes -= 8;
        owner.stat.moveRes -= 8;
    }
}
