package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.UpgradeAction;

public class CheeseBall extends AbstractItem {

    private static final String ID = "CheeseBall";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public CheeseBall(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.speed += 5;
        owner.stat.debuRes += 0.1f;
    }

    @Override
    public void onRemove() {
        owner.stat.speed -= 5;
        owner.stat.debuRes -= 0.1f;
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new UpgradeAction(owner.hand));
    }
}
