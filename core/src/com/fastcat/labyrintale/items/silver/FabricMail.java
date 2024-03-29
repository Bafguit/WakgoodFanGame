package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class FabricMail extends AbstractItem {

    private static final String ID = "FabricMail";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public FabricMail(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        owner.stat.moveRes += 10;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        owner.stat.moveRes -= 10;
    }

    public int onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        top(new ApplyStatusAction(new EnduranceStatus(1), null, owner, true));
        flash();
        return damage;
    }
}
