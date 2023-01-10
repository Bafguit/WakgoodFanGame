package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.BurnStatus;

public class Bomb extends AbstractItem {

    private static final String ID = "Bomb";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public Bomb(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new BurnStatus(3), null, AbstractSkill.SkillTarget.ENEMY_ALL, true));
        flash();
    }
}
