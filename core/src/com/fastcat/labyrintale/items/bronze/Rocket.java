package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class Rocket extends AbstractItem {

    private static final String ID = "Rocket";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Rocket(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
    }

    @Override
    public void atBattleStart() {
        top(new BlockAction(owner, AbstractSkill.SkillTarget.PLAYER_ALL, 3));
        flash();
    }
}
