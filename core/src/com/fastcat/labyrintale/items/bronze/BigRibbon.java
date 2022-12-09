package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class BigRibbon extends AbstractItem {

    private static final String ID = "BigRibbon";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public BigRibbon(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.spell += 1;
    }

    @Override
    public void onRemove() {
        owner.stat.spell -= 1;
    }

    @Override
    public void atBattleStart() {
        flash();
        top(new ApplyStatusAction(new EnduranceStatus(5), owner, AbstractSkill.SkillTarget.SELF, false));
    }
}
