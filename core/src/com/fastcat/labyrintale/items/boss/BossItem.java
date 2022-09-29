package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem extends AbstractItem {

    private static final String ID = "BossItem";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(-10);
        owner.stat.attack += 5;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(10);
        owner.stat.attack -= 5;
    }
}
