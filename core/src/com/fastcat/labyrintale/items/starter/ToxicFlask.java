package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class ToxicFlask extends AbstractItem {

    private static final String ID = "ToxicFlask";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public ToxicFlask(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    public float onAttackedMultiply(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        return attacker != null && attacker.hasDebuff() ? 0.7f : 1.0f;
    }
}
