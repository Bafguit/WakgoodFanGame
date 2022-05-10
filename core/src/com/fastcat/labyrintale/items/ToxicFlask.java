package com.fastcat.labyrintale.items;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BlindStatus;
import com.fastcat.labyrintale.status.InfectionStatus;

public class ToxicFlask extends AbstractItem {

    private static final String ID = "ToxicFlask";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public ToxicFlask(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new InfectionStatus(1), owner, AbstractSkill.SkillTarget.E_ALL, true));
    }
}
