package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem3 extends AbstractItem {

    private static final String ID = "BossItem3";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem3(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(-10);
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(10);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new HealAction(owner, AbstractSkill.SkillTarget.SELF, owner.maxHealth, false));
    }
}
