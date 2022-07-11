package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.LethargyStatus;

public class Coffin extends AbstractItem {

    private static final String ID = "Coffin";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Coffin(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(2);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-2);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new LethargyStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_ALL, false));
    }
}
