package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BlindStatus;

public class SandClock extends AbstractItem {

    private static final String ID = "SandClock";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public SandClock(AbstractPlayer owner) {
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

    public void atBattleStart() {
        top(new ApplyStatusAction(new BlindStatus(), null, AbstractSkill.SkillTarget.ENEMY_ALL, true));
        flash();
    }
}
