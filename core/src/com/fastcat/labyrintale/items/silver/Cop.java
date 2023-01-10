package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ParalyzedStatus;
import com.fastcat.labyrintale.status.ProvokeStatus;
import com.fastcat.labyrintale.status.ShockStatus;

public class Cop extends AbstractItem {

    private static final String ID = "Cop";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public Cop(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.speed += 2;
        owner.stat.critical += 10;
    }

    @Override
    public void onRemove() {
        owner.stat.speed -= 2;
        owner.stat.critical -= 10;
    }

    public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
        if (type == AbstractEntity.DamageType.NORMAL || type == AbstractEntity.DamageType.COUNTER) {
            top(new ApplyStatusAction(new ParalyzedStatus(1), null, target, true));
            top(new ApplyStatusAction(new ShockStatus(1), null, target, true));
            flash();
        }
    }
}
