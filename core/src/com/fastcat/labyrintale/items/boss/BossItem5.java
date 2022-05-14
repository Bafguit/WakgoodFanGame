package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem5 extends AbstractItem {

    private static final String ID = "BossItem";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem5(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        AbstractLabyrinth.modifyMaxEnergy(1);
        AbstractLabyrinth.modifySelection(1);
    }

    @Override
    public void onRemove() {
        AbstractLabyrinth.modifyMaxEnergy(-1);
        AbstractLabyrinth.modifySelection(-1);
    }
}
