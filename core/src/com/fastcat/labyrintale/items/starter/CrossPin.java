package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.LethargyStatus;

public class CrossPin extends AbstractItem {

    private static final String ID = "CrossPin";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public CrossPin(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new LethargyStatus(1, false), owner, AbstractSkill.SkillTarget.E_L, true));
    }
}
