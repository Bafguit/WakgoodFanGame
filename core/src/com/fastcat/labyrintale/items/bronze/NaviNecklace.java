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
        System.out.println("Navi!");
        owner.modifyMaxHealth(2);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-2);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new UnfortifiedStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_ALL, false));
    }
}
