package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.ScarStatus;
import com.fastcat.labyrintale.status.SpikeStatus;

public class Kunai extends AbstractItem {

    private static final String ID = "Kunai";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public Kunai(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.attack += 1;
        owner.stat.multiply += 40;
    }

    @Override
    public void onRemove() {
        owner.stat.attack -= 1;
        owner.stat.multiply -= 40;
    }

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new ScarStatus(1), null, AbstractSkill.SkillTarget.ENEMY_ALL, true));
        flash();
    }
}
