package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class OldPistol extends AbstractItem {

    private static final String ID = "OldPistol";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public OldPistol(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.moveRes += 20;
    }

    @Override
    public void onRemove() {
        owner.stat.moveRes -= 20;
    }

    public void onMove(AbstractEntity source) {
        bot(new ApplyStatusAction(new CourageStatus(2), owner, owner, true));
    }
}
