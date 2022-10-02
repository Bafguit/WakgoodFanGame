package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class IceFrag extends AbstractItem {

    private static final String ID = "IceFrag";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public IceFrag(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.speed -= 30;
        owner.stat.debuRes += 0.3f;
        owner.stat.neutRes += 0.3f;
        owner.stat.moveRes += 0.3f;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(10);
        owner.stat.speed += 30;
        owner.stat.debuRes -= 0.3f;
        owner.stat.neutRes -= 0.3f;
        owner.stat.moveRes -= 0.3f;
    }
}
