package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class BossItem4 extends AbstractItem {

    private static final String ID = "BossItem4";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem4(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        AbstractLabyrinth.modifyMaxEnergy(1);
    }

    @Override
    public void onRemove() {
        AbstractLabyrinth.modifyMaxEnergy(-1);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new AttackStatus(1), owner, AbstractSkill.SkillTarget.E_ALL, true));
    }
}
