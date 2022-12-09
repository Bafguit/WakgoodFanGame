package com.fastcat.labyrintale.items.shop;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.screens.shop.ShopScreen;

public class Protection extends AbstractItem {

    private static final String ID = "Protection";
    private static final ItemRarity RARITY = ItemRarity.SHOP;

    public Protection(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        if (Labyrintale.getBaseScreen() instanceof ShopScreen) {
            Labyrintale.shopScreen.room.roll.price += 0.5f;
        }
        owner.stat.moveRes += 5;
        owner.stat.neutRes += 5;
        owner.stat.debuRes += 5;
    }

    @Override
    public void onRemove() {
        owner.stat.moveRes -= 5;
        owner.stat.neutRes -= 5;
        owner.stat.debuRes -= 5;
    }
}
