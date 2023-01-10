package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.SpikeStatus;

public class Nobles extends AbstractItem {

    private static final String ID = "Nobles";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Nobles(AbstractPlayer owner) {
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

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new SpikeStatus(3), null, owner, true));
        flash();
    }
}
