package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;

public class DrDopamine extends AbstractItem {

    private static final String ID = "dopa";
    private static final ItemRarity RARITY = ItemRarity.ADVISOR;

    public DrDopamine() {
        super(ID, null, RARITY);
    }
}
