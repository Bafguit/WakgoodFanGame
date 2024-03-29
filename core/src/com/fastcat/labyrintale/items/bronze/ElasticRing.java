package com.fastcat.labyrintale.items.bronze;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class ElasticRing extends AbstractItem {

    private static final String ID = "ElasticRing";
    private static final ItemRarity RARITY = ItemRarity.BRONZE;

    public ElasticRing(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(5);
        owner.stat.moveRes += 10;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-5);
        owner.stat.moveRes -= 10;
    }

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new EnduranceStatus(2), null, AbstractSkill.SkillTarget.PLAYER_ALL, true));
        flash();
    }
}
