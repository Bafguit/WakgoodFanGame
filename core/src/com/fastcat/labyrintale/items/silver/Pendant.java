package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HealAction;

public class Pendant extends AbstractItem {

    private static final String ID = "Pendant";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public Pendant(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        owner.stat.neutRes += 5;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        owner.stat.neutRes -= 5;
    }

    @Override
    public void atBattleEnd() {
        top(new HealAction(null, AbstractSkill.SkillTarget.PLAYER_ALL, 2));
        flash();
    }
}
