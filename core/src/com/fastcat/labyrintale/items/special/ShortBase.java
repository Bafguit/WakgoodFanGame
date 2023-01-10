package com.fastcat.labyrintale.items.special;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractStatus;

public class ShortBase extends AbstractItem {

    private static final String ID = "ShortBase";
    private static final ItemRarity RARITY = ItemRarity.SPECIAL;

    public ShortBase(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.speed += 10;
    }

    @Override
    public void onRemove() {
        owner.stat.speed -= 10;
    }

    @Override
    public void onApplyStatus(AbstractStatus s, Array<AbstractEntity> target) {
        if (s.type == AbstractStatus.StatusType.BUFF && s.hasAmount) {
            s.amount++;
        }
    }
}
