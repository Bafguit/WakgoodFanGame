package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

public class Mosquito extends AbstractItem {

    private static final String ID = "Mosquito";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public Mosquito(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.attack += 2;
    }

    @Override
    public void onRemove() {
        owner.stat.attack -= 2;
    }

    public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL || type == AbstractEntity.DamageType.COUNTER) {
            owner.heal(1);
        }
    }
}
