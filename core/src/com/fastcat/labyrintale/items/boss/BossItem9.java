package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem9 extends AbstractItem {

    private static final String ID = "BossItem9";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem9(AbstractPlayer owner) {
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
