package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.ParalyzedStatus;
import com.fastcat.labyrintale.status.ShockStatus;

public class Pirate extends AbstractItem {

    private static final String ID = "Pirate";
    private static final ItemRarity RARITY = ItemRarity.GOLD;
    private AbstractEntity e;

    public Pirate(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.multiply += 50;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
        owner.stat.multiply -= 50;
    }

    public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL || type == AbstractEntity.DamageType.COUNTER) {
            top(new ApplyStatusAction(new BurnStatus(2), null, target, true));
            flash();
        }
    }
}
