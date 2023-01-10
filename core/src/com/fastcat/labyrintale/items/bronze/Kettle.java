package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;
import com.fastcat.labyrintale.status.ProvokeStatus;

public class Kettle extends AbstractItem {

    private static final String ID = "Kettle";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Kettle(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(8);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-8);
    }

    public void startOfTurn() {
        top(new ApplyStatusAction(new EnduranceStatus(1), null, owner, true));
        flash();
    }
}
