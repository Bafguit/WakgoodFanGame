package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class NaviNecklace extends AbstractItem {

    private static final String ID = "NaviNecklace";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public NaviNecklace(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        owner.stat.critical += 10;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        owner.stat.critical -= 10;
    }

    @Override
    public void atBattleStart() {
        flash();
        top(new ApplyStatusAction(new UnfortifiedStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_ALL, true));
    }
}
