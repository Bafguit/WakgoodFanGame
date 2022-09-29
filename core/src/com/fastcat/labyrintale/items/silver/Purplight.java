package com.fastcat.labyrintale.items.silver;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class Purplight extends AbstractItem {

    private static final String ID = "Purplight";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public Purplight(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.attack += 2;
        owner.stat.speed += 10;
        owner.stat.debuRes -= 0.05f;
    }

    @Override
    public void onRemove() {
        owner.stat.attack -= 2;
        owner.stat.speed -= 10;
        owner.stat.debuRes += 0.05f;
    }
}
