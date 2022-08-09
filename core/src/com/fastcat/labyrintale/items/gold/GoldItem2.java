package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class GoldItem2 extends AbstractItem {

    private static final String ID = "GoldItem2";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public GoldItem2(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(8);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-8);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new ImmuneStatus(1), owner, AbstractSkill.SkillTarget.PLAYER_ALL, true));
    }
}
