package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;

public class AutoFire extends AbstractItem {

    private static final String ID = "AutoFire";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public AutoFire(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.stat.speed += 3;
    }

    @Override
    public void onRemove() {
        owner.stat.speed -= 3;
    }

    @Override
    public void startOfTurn() {
        top(new AttackAction(
                null,
                AbstractSkill.SkillTarget.ENEMY_ALL,
                3,
                AbstractEntity.DamageType.SPIKE,
                AttackAction.AttackType.LIGHT,
                true));
        flash();
    }
}
