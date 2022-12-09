package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class Brainize extends AbstractItem {

    private static final String ID = "Brainize";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public Brainize(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        owner.stat.debuRes += 20;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        owner.stat.debuRes -= 20;
    }

    @Override
    public void atBattleStart() {
        flash();
        top(new ApplyStatusAction(new ImmuneStatus(1), owner, AbstractSkill.SkillTarget.PLAYER_ALL, true));
    }
}
