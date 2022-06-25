package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class OldSword extends AbstractItem {

    private static final String ID = "OldSword";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public OldSword(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new CourageStatus(1), owner, AbstractSkill.SkillTarget.SELF, false));
    }
}
