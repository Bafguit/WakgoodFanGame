package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class GoldItem3 extends AbstractItem {

    private static final String ID = "Item1";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public GoldItem3(AbstractPlayer owner) {
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
        bot(new BlockAction(owner, AbstractSkill.SkillTarget.SELF, 15));
    }
}
