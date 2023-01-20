package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ProvokeStatus;

public class CrossPin extends AbstractItem {

    private static final String ID = "CrossPin";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public CrossPin(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
    }

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new ProvokeStatus(owner), null, owner, true));
        flash();
    }
}
