package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class BattleAxe extends AbstractItem {

    private static final String ID = "BattleAxe";
    private static final ItemRarity RARITY = ItemRarity.STARTER;

    public BattleAxe(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void atBattleStart() {
        flash();
        ActionHandler.bot(new AttackAction(owner, AbstractSkill.SkillTarget.ENEMY_FIRST, 3, AbstractEntity.DamageType.SPIKE, AttackAction.AttackType.LIGHT, true));
    }
}
