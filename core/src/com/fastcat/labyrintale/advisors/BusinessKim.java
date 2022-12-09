package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class BusinessKim extends AbstractItem {

    private static final String ID = "business";
    private static final ItemRarity RARITY = ItemRarity.ADVISOR;

    public BusinessKim() {
        super(ID, null, RARITY);
    }

    public void atBattleStart() {
        bot(new ApplyStatusAction(new EnduranceStatus(2), null, AbstractSkill.SkillTarget.PLAYER_FIRST_TWO, false));
    }
}
