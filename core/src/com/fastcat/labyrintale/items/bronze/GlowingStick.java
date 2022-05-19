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
        owner.modifyMaxHealth(2);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-2);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new CourageStatus(3), owner, AbstractSkill.SkillTarget.SELF, true));
    }
}
