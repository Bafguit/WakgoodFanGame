package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.status.BlindStatus;
import com.fastcat.labyrintale.status.CounterStatus;

public class Chiwawa extends AbstractItem {

    private static final String ID = "Chiwawa";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public Chiwawa(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.critical += 20;
        owner.stat.neutRes += 10;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
        owner.stat.critical -= 20;
        owner.stat.neutRes -= 10;
    }

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new CounterStatus(5), null, owner, true));
        flash();
    }
}
