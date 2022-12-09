package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ResistPlusStatus;

public class LifePotion extends AbstractItem {

    private static final String ID = "LifePotion";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public LifePotion(AbstractPlayer owner) {
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

    @Override
    public void atBattleStart() {
        flash();
        top(new ApplyStatusAction(new ResistPlusStatus(1), owner, owner, true));
    }
}
