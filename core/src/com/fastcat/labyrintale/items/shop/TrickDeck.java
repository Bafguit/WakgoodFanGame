package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class TrickDeck extends AbstractItem {

    private static final String ID = "TrickDeck";
    private static final ItemRarity RARITY = ItemRarity.SHOP;

    public TrickDeck(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.speed += 1;
        owner.stat.critical += 0.1f;
    }

    @Override
    public void onRemove() {
        owner.stat.speed -= 1;
        owner.stat.critical -= 0.1f;
    }

    @Override
    public int onGainGold(int amount) {
        return (int) (((float) amount) * 1.2f);
    }
}
