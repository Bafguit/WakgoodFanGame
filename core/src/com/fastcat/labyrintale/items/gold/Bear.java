package com.fastcat.labyrintale.items.gold;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class Bear extends AbstractItem {

    private static final String ID = "Bear";
    private static final ItemRarity RARITY = ItemRarity.GOLD;

    public Bear(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        owner.modifyMaxHealth(10);
        owner.stat.spell += 2;
    }

    @Override
    public void onRemove() {
        owner.modifyMaxHealth(-10);
        owner.stat.spell -= 2;
    }

    @Override
    public int onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        if (damage > 0 && (type == AbstractEntity.DamageType.NORMAL || type == AbstractEntity.DamageType.COUNTER)) {
            top(new BlockAction(null, AbstractSkill.getTargets(owner, AbstractSkill.SkillTarget.OTHER), 2));
            flash();
            return damage;
        }
        return damage;
    }
}
