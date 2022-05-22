package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.SpellStatus;

public class BossItem2 extends AbstractItem {

    private static final String ID = "BossItem";
    private static final ItemRarity RARITY = ItemRarity.BOSS;

    public BossItem2(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        flash();
        bot(new ApplyStatusAction(new AttackStatus(-5), owner, AbstractSkill.SkillTarget.SELF, true));
        bot(new ApplyStatusAction(new SpellStatus(5), owner, AbstractSkill.SkillTarget.SELF, true));
    }
}
