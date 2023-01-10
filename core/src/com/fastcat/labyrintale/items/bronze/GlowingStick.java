package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class GlowingStick extends AbstractItem {

    private static final String ID = "GlowingStick";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public GlowingStick(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.attack += 1;
    }

    @Override
    public void onRemove() {
        owner.stat.attack -= 1;
    }

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new CourageStatus(5), null, owner, true));
        flash();
    }
}
