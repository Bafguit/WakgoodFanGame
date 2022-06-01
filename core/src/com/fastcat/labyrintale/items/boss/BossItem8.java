package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem8 extends AbstractItem {

    private static final String ID = "BossItem8";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem8(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.setMaxHealth(10, true);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new AttackStatus(5), owner, AbstractSkill.SkillTarget.SELF, true));
    }
}