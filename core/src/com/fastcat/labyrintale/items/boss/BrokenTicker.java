package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.actions.HealAction;

public class BrokenTicker extends AbstractItem {

    private static final String ID = "BrokenTicker";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BrokenTicker(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(-10);
        owner.stat.debuRes += 10;
        owner.stat.neutRes += 10;
        owner.stat.moveRes += 10;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(10);
        owner.stat.debuRes -= 10;
        owner.stat.neutRes -= 10;
        owner.stat.moveRes -= 10;
    }

    @Override
    public void atBattleStart() {
        top(new HealAction(owner, owner, owner.maxHealth));
        flash();
    }
}
