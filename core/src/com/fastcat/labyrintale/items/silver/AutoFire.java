package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class AutoFire extends AbstractItem {

    private static final String ID = "AutoFire";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public AutoFire(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.speed += 15;
    }

    @Override
    public void onRemove() {
        owner.stat.speed -= 15;
    }

    @Override
    public void startOfTurn() {
        flash();
        ActionHandler.bot(new AttackAction(owner, AbstractSkill.SkillTarget.ENEMY_ALL, 2, AbstractEntity.DamageType.SPIKE, AttackAction.AttackType.LIGHT, true));
    }
}
