package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;

public class Haerusuk extends AbstractItem {

    private static final String ID = "rusuk";
    private static final ItemRarity RARITY = ItemRarity.ADVISOR;

    public Haerusuk() {
        super(ID, null, RARITY);
    }
}
