package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class Bible extends AbstractItem {

    private static final String ID = "Bible";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public Bible(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new CourageStatus(1), owner, AbstractSkill.SkillTarget.RIGHT, false));
    }
}
