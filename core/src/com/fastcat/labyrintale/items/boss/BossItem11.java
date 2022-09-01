package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem11 extends AbstractItem {

    private static final String ID = "BossItem";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem11(AbstractPlayer owner) {
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
