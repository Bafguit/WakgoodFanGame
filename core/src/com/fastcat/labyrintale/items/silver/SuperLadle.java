package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class SuperLadle extends AbstractItem {

    private static final String ID = "SuperLadle";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public SuperLadle(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.attack++;
        }
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.attack--;
        }
    }
}
