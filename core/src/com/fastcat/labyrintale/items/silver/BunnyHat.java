package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.SpellStatus;

public class BunnyHat extends AbstractItem {

    private static final String ID = "BunnyHat";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public BunnyHat(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(4);
        owner.stat.attack++;
        owner.stat.spell++;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-4);
        owner.stat.attack--;
        owner.stat.spell--;
    }
}
