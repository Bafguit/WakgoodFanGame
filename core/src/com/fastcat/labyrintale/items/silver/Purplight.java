package com.fastcat.labyrintale.items.silver;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.SpellStatus;

public class Purplight extends AbstractItem {

    private static final String ID = "Purplight";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public Purplight(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(4);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-4);
    }

    @Override
    public void onApplyStatus(AbstractStatus s, Array<AbstractEntity> t) {
        if(s.id.equals("Infection")) {
            flash();
            s.amount++;
        }
    }
}
