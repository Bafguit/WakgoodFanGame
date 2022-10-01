package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem12 extends AbstractItem {

    private static final String ID = "TotoDeck";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem12(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.setMaxHealth(10, true);
        owner.stat.attack += 5;
    }

    @Override
    public void onRemove() {
        owner.stat.attack -= 5;
    }
}
